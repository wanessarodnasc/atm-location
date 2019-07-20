package br.atm.location.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atm.location.model.User;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

}
