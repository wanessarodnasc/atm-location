package br.atm.location.service.imp;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.atm.location.exception.BusinessException;
import br.atm.location.form.AtmForm;
import br.atm.location.model.Atm;
import br.atm.location.model.AtmList;
import br.atm.location.repository.AtmRepository;
import br.atm.location.service.AtmService;
/**
* 
*
* @author Wanessa Nascimento
*/
@Service
public class AtmServiceImp implements AtmService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AtmServiceImp.class);
	
	@Autowired
	private AtmRepository repository;

	@Override
	public AtmList findAll() {
		LOGGER.info("Get type list");
		List<Atm> atms = repository.findAll();
		return new AtmList(atms);
	}

	@Override
	public AtmList findAllByType(String type) throws BusinessException {
		LOGGER.info(MessageFormat.format("Get atm by type - Type : {0}", type));
		Optional<List<Atm>> atms = repository.findByType(type);
		if(!atms.isPresent()) {
			throw new BusinessException("Does not have ATM in this city.");
		}
		return new AtmList(atms.get());
	}


	@Override
	public AtmList findByAddressCity(String city) throws BusinessException {
		LOGGER.info(MessageFormat.format("Get atm by type - City : {0}", city));
		Optional<List<Atm>> atms= repository.findByAddressCity(city);
		if(!atms.isPresent()) {
			throw new BusinessException("Does not have ATM in this city.");
		}
		return new AtmList(atms.get());
	}

	@Override
	public Atm register(AtmForm form) {
		LOGGER.info(MessageFormat.format("Register ATM : {0}", form));
		return repository.save(new Atm(form));
	}
}
