package br.atm.location.form;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.atm.location.model.Address;

/**
* 
* @author Wanessa Nascimento
*
*/
public class AtmForm implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Type is mandatory")
	private String type;
	
	private Address address;

	public String getType() {
		return type;
	}

	public Address getAddress() {
		return address;
	}
}
