package br.atm.location.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.atm.location.config.security.TokenService;
import br.atm.location.controller.dto.TokenDto;
import br.atm.location.form.LoginForm;

/**
* 
* @author Wanessa Nascimento
*
*/
@RestController
@RequestMapping("/generate-token")
public class AutenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	/**
	 * Generate Token by authentication user.
	 *
	 * @param LoginForm is a required parameter
	 * @return A TokenDto with a valid token
	 * 
	 */
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
		Authentication authentication = authManager.authenticate(form.converter());
		return ResponseEntity.ok(new TokenDto(tokenService.generateToken(authentication)));
	}
}
