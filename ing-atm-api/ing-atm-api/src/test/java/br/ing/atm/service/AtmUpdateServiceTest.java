package br.ing.atm.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.atm.location.model.Atm;
import br.atm.location.repository.AtmRepository;
import br.atm.location.service.imp.AtmUpdateService;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmUpdateServiceTest {
	
	@Autowired
	private AtmUpdateService service;
	
	@Autowired
	private AtmRepository repository;
	
	@Test
	public void testAtmUpdateService() {
		service.updateAtmLocationIng();
		List<Atm> atms = repository.findAll();
		assertEquals(false, atms.isEmpty());
	}
	
	@Test
	public void testAtmUpdateServiceDuplicate() {
		service.updateAtmLocationIng();
		List<Atm> atms = repository.findAll();
		int sizeOne = atms.size();
		service.updateAtmLocationIng();
		List<Atm> atmsTwo = repository.findAll();
		int sizeTwo = atmsTwo.size();
		assertEquals(sizeOne, sizeTwo);
	}
}
