package br.ing.atm.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.atm.location.controller.dto.AddressDto;
import br.atm.location.controller.dto.AtmDto;
import br.atm.location.exception.BusinessException;
import br.atm.location.model.Address;
import br.atm.location.model.Atm;
import br.atm.location.model.AtmList;
import br.atm.location.model.GeoLocation;
import br.atm.location.repository.AtmRepository;
import br.atm.location.service.imp.AtmServiceImp;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmServiceTest {
	
	@Autowired
	private AtmServiceImp service;
	
	@Autowired
	private AtmRepository repository;
	
	private Address adrress;
	
	@Before
	public void beforeTest() {
		adrress = new Address(new AddressDto("street", "housenumber", "postalcode", "Amsterdam", new GeoLocation("lat", "lng")));
		repository.save(new Atm(new AtmDto("ING", adrress)));
	}
	
	@Test
	public void listAtmsByCityTest() throws BusinessException {
		AtmList atms = service.findByAddressCity("Amsterdam");
		assertNotNull(atms.getResults().size());;
	}
	
	@Test
	public void listAtmsByTypeTest() throws BusinessException {
		AtmList atms = service.findAllByType("ING");
		assertNotNull(atms.getResults().size());
	}
	
	@Test
	public void listAtmsByWrongTypeTest() throws BusinessException {
		try {
			service.findAllByType("INGGG");
		} catch (BusinessException e) {
			boolean erro = true;
			assertEquals(true, erro);
		}
	}
	
	@Test
	public void findAllTest() throws BusinessException {
		AtmList atms = service.findAll();
		assertNotNull(atms.getResults().size());;
	}
}
