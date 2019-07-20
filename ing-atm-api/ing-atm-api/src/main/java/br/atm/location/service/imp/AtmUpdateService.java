package br.atm.location.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import br.atm.location.model.Atm;
import br.atm.location.repository.AtmRepository;
import br.atm.location.service.client.imp.AtmClientServiceImp;

/**
* 
* @author Wanessa Nascimento
*
*/
@Service
public class AtmUpdateService {

	@Autowired
	private AtmClientServiceImp serviceClient;

	@Autowired
	private AtmRepository repository;

	@CacheEvict(value = "atmList", allEntries = true)
	public void updateAtmLocationIng() {
		List<Atm> atms = Atm.converter(serviceClient.getAtmLocation());
		repository.saveAll(atms);
	}
}
