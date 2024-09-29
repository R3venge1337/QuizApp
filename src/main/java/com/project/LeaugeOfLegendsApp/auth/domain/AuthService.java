package com.project.LeaugeOfLegendsApp.auth.domain;

import com.project.LeaugeOfLegendsApp.auth.AuthFacade;
import com.project.LeaugeOfLegendsApp.auth.UserFacade;
import com.project.LeaugeOfLegendsApp.auth.dto.CreateUserForm;
import com.project.LeaugeOfLegendsApp.auth.dto.JwtResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.LoginRequest;
import com.project.LeaugeOfLegendsApp.auth.dto.UserResponse;
import com.project.LeaugeOfLegendsApp.auth.dto.UserWithAccount;
import com.project.LeaugeOfLegendsApp.auth.dto.VerifyUserForm;
import com.project.LeaugeOfLegendsApp.auth.security.JwtTokenUtil;
import com.project.LeaugeOfLegendsApp.exceptions.AlreadyExistException;
import com.project.LeaugeOfLegendsApp.exceptions.AlreadyVerifiedException;
import com.project.LeaugeOfLegendsApp.exceptions.NotFoundException;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.Validate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import static com.project.LeaugeOfLegendsApp.auth.domain.AccountService.ErrorMessages.ACCOUNT_NOT_FOUND;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.ACCOUNT_NOT_VERIFIED;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.ACCOUNT_VERIFIED;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.EMAIL_IS_TAKEN;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.LOGIN_FIELDS_EMPTY;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.REGISTER_FORM_EMPTY;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.USER_IS_TAKEN;
import static com.project.LeaugeOfLegendsApp.auth.domain.AuthService.ErrorMessages.VERIFICATION_CODE_INVALID;
import static com.project.LeaugeOfLegendsApp.auth.domain.DomainMapper.mapToUserResponse;
import static com.project.LeaugeOfLegendsApp.auth.domain.UserService.ErrorMessages.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
class AuthService implements AuthFacade {


    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserFacade userFacade;
    private final EmailService emailService;

    static final class ErrorMessages {
        static final String USER_IS_TAKEN = "Error: Username is already taken!: %s";
        static final String EMAIL_IS_TAKEN = "Error: Email is already taken!: %s";
        static final String ROLE_NOT_FOUND = "Error: ROLE USER has not been found!: %s";
        static final String ACCOUNT_VERIFIED = "Error: Account has been verified!: %s";
        static final String ACCOUNT_NOT_VERIFIED = "Error: Account has been not verified! check email";
        static final String VERIFICATION_CODE_INVALID = "Error: Verification code expired or sth went wrong!";
        static final String LOGIN_FIELDS_EMPTY = "Error: Username and password cannot be null or empty!";
        static final String REGISTER_FORM_EMPTY = "Error: Some register fields have wrong data!";
    }

    public JwtResponse authenticateUser(@Valid @RequestBody final LoginRequest loginRequest) {
        Validate.notNull(loginRequest, LOGIN_FIELDS_EMPTY);
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));
        UserWithAccount user = userFacade.findByUsername(loginRequest.username());

        if (!user.isEnabled()) {
            throw new AlreadyVerifiedException(ACCOUNT_NOT_VERIFIED);
        }

        String jwt = jwtTokenUtil.generateToken(user);


        SecurityContextHolder.getContext().setAuthentication(auth);

        return new JwtResponse(jwt);
    }

    public UserResponse registerUser(@Valid @RequestBody final CreateUserForm createForm) {
        Validate.notNull(createForm, REGISTER_FORM_EMPTY);

        if (accountRepository.existsByUsername(createForm.username())) {
            throw new AlreadyExistException(USER_IS_TAKEN, createForm.username());
        }

        if (accountRepository.existsByEmail(createForm.email())) {
            throw new AlreadyExistException(EMAIL_IS_TAKEN, createForm.email());
        }

        String defaultRole = "ROLE_USER";
        Role userRole = roleRepository.findByName(defaultRole)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.ROLE_NOT_FOUND, defaultRole));

        Set<Permission> defaultPermissions = new HashSet<>();
        defaultPermissions.add(new Permission("READ"));
        defaultPermissions.add(new Permission("WRITE"));
        defaultPermissions.forEach(permission -> {
            Permission ent = permissionRepository.save(permission);
            userRole.getPermissions().add(ent);
        });

        Account account = new Account(createForm.username(), createForm.email(),
                passwordEncoder.encode(createForm.password()), false, false, Set.of(userRole));

        account.setVerificationCode(generateVerificationCode());
        account.setVerificationCodeExpiredAt(LocalDateTime.now().plusMinutes(15));

        Account entity = accountRepository.save(account);
        User user = new User(createForm.name(), createForm.secondName(), createForm.surname(), createForm.yearOfBirth(), entity);

        sendVerificationEmail(account);
        return mapToUserResponse(userRepository.insert(user));
    }


    public void verifyUser(final VerifyUserForm input) {
        Optional<Account> optionalUser = accountRepository.findByEmail(input.email());
        if (optionalUser.isPresent()) {
            Account account = optionalUser.get();
            if (account.getVerificationCodeExpiredAt().isBefore(LocalDateTime.now())) {
                throw new AlreadyVerifiedException(VERIFICATION_CODE_INVALID);
            }
            if (account.getVerificationCode().equals(input.verificationCode())) {
                account.setIsEnabled(true);
                account.setVerificationCode(null);
                account.setVerificationCodeExpiredAt(null);
                accountRepository.save(account);
            } else {
                throw new AlreadyVerifiedException(VERIFICATION_CODE_INVALID);
            }
        } else {
            throw new NotFoundException(ACCOUNT_NOT_FOUND);
        }
    }

    public void resendVerificationCode(final String email) {
        Optional<Account> optionalUser = accountRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            Account account = optionalUser.get();
            if (account.getIsEnabled()) {
                throw new AlreadyVerifiedException(ACCOUNT_VERIFIED, account.getUsername());
            }
            account.setVerificationCode(generateVerificationCode());
            account.setVerificationCodeExpiredAt(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(account);
            accountRepository.save(account);
        } else {
            throw new NotFoundException(USER_NOT_FOUND);
        }
    }

    private void sendVerificationEmail(final Account account) {
        String subject = "Account Verification";
        String verificationCode = "VERIFICATION CODE " + account.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Welcome to our app!</h2>"
                + "<p style=\"font-size: 16px;\">Please enter the verification code below to continue:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Verification Code:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            emailService.sendVerificationEmail(account.getEmail(), subject, htmlMessage);
        } catch (MessagingException e) {
            // Handle email sending exception
            e.printStackTrace();
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }
}
