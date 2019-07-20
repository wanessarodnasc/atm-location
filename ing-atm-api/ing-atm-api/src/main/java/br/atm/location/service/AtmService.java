package br.atm.location.service;

import br.atm.location.exception.BusinessException;
import br.atm.location.form.AtmForm;
import br.atm.location.model.Atm;
import br.atm.location.model.AtmList;

/**
 * 
 * @author Wanessa Nascimento
 *
 */
public interface AtmService {

	AtmList findAll();

	AtmList findAllByType(String type) throws BusinessException;

	AtmList findByAddressCity(String city)throws BusinessException;

	Atm register(AtmForm form);

}
