package br.atm.location.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.atm.location.service.imp.AtmServiceImp;
import br.atm.location.service.imp.AtmUpdateService;

/**
* 
* @author Wanessa Nascimento
*
*/
@Configuration
public class UpdateAtmListConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(AtmServiceImp.class);

	@Autowired
	private AtmUpdateService service;

	@Bean
	public void updateAtmList() {
		LOGGER.info("Update ATM list.");
		service.updateAtmLocationIng();
	}
}
