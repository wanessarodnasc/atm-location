package br.atm.location.service.client;

import java.util.List;

import br.atm.location.controller.dto.AtmDto;

/**
 *
 * This interface provide a contract to consult atm location service. Is possible
 * implement to more than one bank.
 *
 * @author Wanessa Nascimento
 * 
 */
public interface AtmClientService {
	
	List<AtmDto> getAtmLocation();

}
