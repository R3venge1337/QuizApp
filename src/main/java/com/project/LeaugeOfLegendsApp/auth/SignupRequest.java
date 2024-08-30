package com.project.LeaugeOfLegendsApp.auth;

import java.util.Set;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class SignupRequest {
	
	 	@NotBlank
	    @Size(min = 3, max = 20)
	    private String username;
	 
	    @NotBlank
	    @Size(max = 50)
	    @Email
	    private String email;
	    
	    private Set<Role> roles;
	    
	    @NotBlank
	    @Size(min = 8, max = 40)
	    private String password;
	  
}
