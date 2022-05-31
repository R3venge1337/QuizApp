package com.project.LeaugeOfLegendsApp.util;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.project.LeaugeOfLegendsApp.model.Role;

import lombok.Data;

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
