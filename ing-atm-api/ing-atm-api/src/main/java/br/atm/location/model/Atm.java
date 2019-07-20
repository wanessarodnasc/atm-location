package br.atm.location.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.atm.location.controller.dto.AddressDto;
import br.atm.location.controller.dto.AtmDto;
import br.atm.location.form.AtmForm;

/**
 * 
 * @author Wanessa Nascimento
 *
 */
@Entity
public class Atm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    public String id;
	
	private String type;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Address address;
	
	public Atm() {
	}
	
	public Atm(AtmDto dto) {
		this.id = getAddressComplete(dto.getType(), dto.getAddress());
		this.type = dto.getType();
		this.address = new Address(dto.getAddress());
	}

	public Atm(AtmForm form) {
		this.id = getAddressComplete(form.getType(), form.getAddress());
		this.type = form.getType();
		this.address = form.getAddress();
	}

	public String getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public Address getAddress() {
		return address;
	}
	
	private String getAddressComplete(String type, AddressDto address) {
		return "Address [street=" + address.getStreet()
		+ ", housenumber=" + address.getHousenumber()
		+ ", postalcode=" + address.getPostalcode()
		+ ", city= " + address.getCity() 
		+ ", type= " + type 
		+ "]";
	}
	
	private String getAddressComplete(String type, Address address) {
		return "Address [street=" + address.getStreet()
		+ ", housenumber=" + address.getHousenumber()
		+ ", postalcode=" + address.getPostalcode()
		+ ", city= " + address.getCity() 
		+ ", type= " + type 
		+ "]";
	}

	public static List<Atm> converter(List<AtmDto> atms) {
		List<Atm> atmsDto = new ArrayList<>();
		for(AtmDto atm : atms) {
			atmsDto.add(new Atm(atm));
		}
		return atmsDto;
	}
}
