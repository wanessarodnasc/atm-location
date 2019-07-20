package br.atm.location.controller.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.atm.location.model.Address;
import br.atm.location.model.Atm;

/**
* 
* @author Wanessa Nascimento
*
*/
public class AtmDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private AddressDto address;
	
	private String type;
	
	public AtmDto() {
	}
	
	public AtmDto(String type, Address adrress) {
		this.address = new AddressDto(adrress);
		this.type = type;
	}

	public AtmDto(Atm atm) {
		this.address = new AddressDto(atm.getAddress());
		this.type = atm.getType();
	}
	
	public AddressDto getAddress() {
		return address;
	}

	public String getType() {
		return type;
	}

	public static List<AtmDto> converter(List<Atm> atms) {
		List<AtmDto> atmsDto = new ArrayList<>();
		for(Atm atm : atms) {
			atmsDto.add(new AtmDto(atm));
		}
		return atmsDto;
	}
}
