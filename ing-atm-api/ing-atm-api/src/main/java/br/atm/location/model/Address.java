package br.atm.location.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import br.atm.location.controller.dto.AddressDto;

/**
 * 
 * @author Wanessa Nascimento
 *
 */
@Entity
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private GeoLocation geoLocation;
	
	@NotBlank(message = "Stree is mandatory")
	private String street;

	@NotBlank(message = "Haouse numeber is mandatory")
	private String housenumber;

	@NotBlank(message = "Postal code is mandatory")
	private String postalcode;

	@NotBlank(message = "City is mandatory")
	private String city;
	
	public Address() {
	}
	
	public Address(GeoLocation geoLocation, String street, String housenumber, String postalcode, String city) {
		this.geoLocation = geoLocation;
		this.street = street;
		this.housenumber = housenumber;
		this.postalcode = postalcode;
		this.city = city;
	}

	public Address(AddressDto dto) {
		this.street = dto.getStreet();
		this.housenumber = dto.getHousenumber();
		this.postalcode = dto.getPostalcode();
		this.city = dto.getCity();
		this.geoLocation = dto.getGeoLocation();
	}

	public GeoLocation getGeoLocation() {
		return geoLocation;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((geoLocation == null) ? 0 : geoLocation.hashCode());
		result = prime * result + ((housenumber == null) ? 0 : housenumber.hashCode());
		result = prime * result + ((postalcode == null) ? 0 : postalcode.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (geoLocation == null) {
			if (other.geoLocation != null)
				return false;
		} else if (!geoLocation.equals(other.geoLocation))
			return false;
		if (housenumber == null) {
			if (other.housenumber != null)
				return false;
		} else if (!housenumber.equals(other.housenumber))
			return false;
		if (postalcode == null) {
			if (other.postalcode != null)
				return false;
		} else if (!postalcode.equals(other.postalcode))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}
}
