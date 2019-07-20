package br.atm.location.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.atm.location.exception.BusinessException;
import br.atm.location.form.UserForm;
import br.atm.location.model.Email;
import br.atm.location.model.Profile;
import br.atm.location.model.User;
import br.atm.location.repository.ProfileRepository;
import br.atm.location.repository.UserRepository;
import br.atm.location.util.EmailUtil;

/**
* 
* @author Wanessa Nascimento
*
*/
@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private EmailUtil emailUtil;
	
	@Value("${service.user.email.exist}")
	private String emailExist;
	
	private String subject = "Access Token Lets Movie API";

	public User registerNewUser(UserForm loginForm) throws BusinessException {
		Optional<User> userExistent = repository.findByEmail(loginForm.getEmail());
		if(userExistent.isPresent()) {
			throw new BusinessException(emailExist); 
		}
		return validateNewUser(loginForm);
	}

	private User validateNewUser(UserForm loginForm) throws BusinessException {
		String passwordGenerated = generateRadomicPassword();
		User user = new User(loginForm, new BCryptPasswordEncoder().encode(passwordGenerated));
		String msg = gerEmailMsg(passwordGenerated, user);
		if(emailUtil.sendMail(new Email(user.getEmail(), subject, msg))) {
			saveProfile(user.getProfile());
			return repository.saveAndFlush(user);
		}
		throw new BusinessException("Erro to validate email .");
	}

	private void saveProfile(List<Profile> profiles) {
		for(Profile profile : profiles) {
			profileRepository.save(profile);
		}
	}

	private String gerEmailMsg(String passwordGenerated, User user) {
		return "Congratilations, " + user.getName() + " now do you have access to our amazing API. "
						+ " Find your creditials : "
						+ " Username :" + user.getEmail() + " Password :" + passwordGenerated;
	}
	
	private String generateRadomicPassword() {
		return UUID.randomUUID().toString().split("-")[0];
	}
}
