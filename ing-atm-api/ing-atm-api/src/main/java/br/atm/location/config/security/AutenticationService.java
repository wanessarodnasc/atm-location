package br.atm.location.config.security;

import java.text.MessageFormat;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.atm.location.model.User;
import br.atm.location.repository.UserRepository;

/**
* 
* @author Wanessa Nascimento
*
*/
@Service
public class AutenticationService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutenticationService.class);
	
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		LOGGER.info(MessageFormat.format("Token requisition - Email: {0}", email));
		Optional<User> user = repository.findByEmail(email);
		if (user.isPresent()) {
			return user.get();
		}
		throw new UsernameNotFoundException("Bad credentials.");
	}
}
