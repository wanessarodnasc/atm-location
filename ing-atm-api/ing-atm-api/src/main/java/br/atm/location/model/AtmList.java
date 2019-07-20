package br.atm.location.model;

import java.io.Serializable;
import java.util.List;

import br.atm.location.controller.dto.AtmDto;

/**
* 
* @author Wanessa Nascimento
*
*/
public class AtmList implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private List<AtmDto> results;

	public AtmList(List<Atm> atms) {
		this.results = AtmDto.converter(atms);
	}

	public List<AtmDto> getResults() {
		return results;
	}
}
