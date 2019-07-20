package br.ing.atm.repository;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.atm.location.controller.dto.AddressDto;
import br.atm.location.controller.dto.AtmDto;
import br.atm.location.model.Address;
import br.atm.location.model.Atm;
import br.atm.location.model.GeoLocation;
import br.atm.location.repository.AtmRepository;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringRunner.class)
@DataJpaTest
public class AtmRepositoryTest {
	
	@Autowired
	private AtmRepository repository;
	
	private Address adrress;
	
	@Before
	public void beforeTest() {
		adrress = new Address(new AddressDto("street", "housenumber", "postalcode", "city", new GeoLocation("lat", "lng")));
	}
	
	@Test
	public void testSave() {
		Atm atm = new Atm(new AtmDto("ING", adrress));
		repository.save(atm);
	}
	
	@Test
	public void testFindAll() {
		repository.saveAll(getListAtm());
        assertEquals(true, repository.findAll().size() > 1);
	}

	@Test
	public void testFindAllById() {
		Atm atm = repository.save(new Atm(new AtmDto("ING", adrress)));
		Optional<Atm> atmReturned = repository.findById(atm.getId());
		assertEquals(true, atmReturned.isPresent());
	}

	@Test
	public void testDeleteInBatch() {
		List<Atm> atms = repository.saveAll(getListAtm());
		repository.deleteInBatch(atms);
		assertEquals(true, repository.findAll().isEmpty());
	}

	@Test
	public void testDeleteAllInBatch() {
		repository.saveAll(getListAtm());
		repository.deleteAllInBatch();
		assertEquals(true, repository.findAll().isEmpty());
	}

	@Test
	public void testGetOne() {
		Atm atm = repository.save(new Atm(new AtmDto("ING", adrress)));
		assertEquals(atm.getId(), repository.getOne(atm.getId()).getId());
	}

	private List<Atm> getListAtm() {
		return Arrays.asList(new Atm(new AtmDto("ING", adrress)), new Atm(new AtmDto("GELDMAAT", adrress)));
	}
}
