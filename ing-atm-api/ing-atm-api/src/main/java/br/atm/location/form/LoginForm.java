package br.atm.location.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * 
 * @author Wanessa Nascimento
 *
 */
public class LoginForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "Password is mandatory")
	private String password;
	
	private String profile = "USER";

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getProfile() {
		return profile;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, password);
	}
}
