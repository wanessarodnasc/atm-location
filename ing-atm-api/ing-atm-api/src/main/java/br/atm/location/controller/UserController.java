package br.atm.location.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.atm.location.exception.BusinessException;
import br.atm.location.form.UserForm;
import br.atm.location.model.User;
import br.atm.location.service.imp.UserService;

/**
* This class is a controller to create a access password. 
*
* @author Wanessa Nascimento
*/

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
    /**
     * Adding a new user to receive a password.
     *
     * @param UserForma name, email is a required parameter can be a movie name, actor name 
     * or any parameter related with the a movie
     * @return A MovieDto with the video information
     * @throws BusinessException 
     */
    @PostMapping
    @Transactional
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserForm form, 
    		UriComponentsBuilder uriBuilder) throws BusinessException {
    	User user = service.registerNewUser(form);
    	URI uri = uriBuilder.buildAndExpand(user.getId()).toUri();
    	return ResponseEntity.created(uri).body(user.getId().toString());
    }
}
