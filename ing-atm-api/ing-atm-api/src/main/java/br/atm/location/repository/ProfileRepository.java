package br.atm.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atm.location.model.Profile;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
