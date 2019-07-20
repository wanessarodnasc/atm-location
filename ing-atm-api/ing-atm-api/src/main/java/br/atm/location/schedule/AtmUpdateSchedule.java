package br.atm.location.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.atm.location.service.imp.AtmUpdateService;

/**
* 
* @author Wanessa Nascimento
*
*/
@Component
@EnableScheduling
public class AtmUpdateSchedule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AtmUpdateSchedule.class);
	
	@Autowired
	private AtmUpdateService service;
	
	@Scheduled(cron = "0 0 0 * * *")
	@CacheEvict(value = "typeList", allEntries = true)
	public void updateAtmLocation() {
		LOGGER.info("Update ATM list by ING API");
		//Here is possible put another banks or locations consulting another services - execute everyday at midnight
		service.updateAtmLocationIng();
	}
}
