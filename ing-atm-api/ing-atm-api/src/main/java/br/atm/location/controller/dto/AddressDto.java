package br.atm.location.controller.dto;

import java.io.Serializable;

import br.atm.location.model.Address;
import br.atm.location.model.GeoLocation;

/**
* 
* @author Wanessa Nascimento
*
*/
public class AddressDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String street;

	private String housenumber;

	private String postalcode;

	private String city;
	
	private GeoLocation geoLocation;
	
	public AddressDto() {
	}
	
	public AddressDto(Address address) {
		this.city = address.getCity();
		this.geoLocation = address.getGeoLocation();
		this.housenumber = address.getHousenumber();
		this.postalcode = address.getPostalcode();
		this.street = address.getStreet();
	}

	public AddressDto(String street, String housenumber, String postalcode, String city, GeoLocation geoLocation) {
		this.street = street;
		this.housenumber = housenumber;
		this.postalcode = postalcode;
		this.city = city;
		this.geoLocation = geoLocation;
	}

	public String getStreet() {
		return street;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public String getCity() {
		return city;
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
	}
}
