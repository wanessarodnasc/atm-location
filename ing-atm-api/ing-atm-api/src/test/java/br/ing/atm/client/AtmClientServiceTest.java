package br.ing.atm.client;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.atm.location.model.Atm;
import br.atm.location.service.client.AtmClientService;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmClientServiceTest {
	
	@Autowired
	private AtmClientService service;
	
	@Test
	public void getAtmLocationTest() {
		List<Atm> atms = Atm.converter(service.getAtmLocation());
		assertNotNull(atms);
	}
}
