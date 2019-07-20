package br.ing.atm.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.atm.location.controller.dto.AddressDto;
import br.atm.location.controller.dto.AtmDto;
import br.atm.location.model.Address;
import br.atm.location.model.Atm;
import br.atm.location.model.GeoLocation;

/**
* 
* @author Wanessa Nascimento
*
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AtmDtoTest {
	
	private Address adrress;
	
	@Before
	public void beforeTest() {
		adrress = new Address(new AddressDto("street", "housenumber", "postalcode", "city", new GeoLocation("lat", "lng")));
	}
	
	@Test
	public void testAtmDtoObject() {
		Atm atm = new Atm(new AtmDto("ING", adrress));
		assertEquals(getAddressComplete(adrress), atm.getId());
	}
	
	private String getAddressComplete(Address address) {
		return "Address [street=" + address.getStreet()
		+ ", housenumber=" + address.getHousenumber()
		+ ", postalcode=" + address.getPostalcode()
		+ ", city= " + address.getCity() 
		+ ", type= " + "ING" 
		+ "]";
	}

}
