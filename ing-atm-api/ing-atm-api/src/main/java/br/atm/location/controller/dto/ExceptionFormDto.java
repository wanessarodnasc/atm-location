package br.atm.location.controller.dto;

import java.io.Serializable;

/**
* 
* @author Wanessa Nascimento
*
*/
public class ExceptionFormDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String field;
	private String error;
	
	public ExceptionFormDto(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}
}
