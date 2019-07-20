package br.atm.location.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.atm.location.model.Atm;

/**
* 
* @author Wanessa Nascimento
*
*/
public interface AtmRepository extends JpaRepository<Atm, String> {

	Optional<List<Atm>> findByType(String type);

	Optional<List<Atm>> findByAddressCity(String city);

}