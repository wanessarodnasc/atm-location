package br.atm.location.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.atm.location.exception.BusinessException;
import br.atm.location.form.AtmForm;
import br.atm.location.model.Atm;
import br.atm.location.model.AtmList;
import br.atm.location.service.AtmService;

/**
 * This class is a controller to provide a rest web service.
 *
 * @author Wanessa Nascimento
 *
 */
@RestController
@RequestMapping("/atms")
public class AtmController {

	@Autowired
	private AtmService service;

	@GetMapping
	public ResponseEntity<AtmList> listAllAtms() {
		AtmList atms = service.findAll();
		return ResponseEntity.ok(atms);
	}
	
	@GetMapping("/type")
	public ResponseEntity<AtmList> listAllAtms(@RequestParam String type) throws BusinessException {
		AtmList atms = service.findAllByType(type);
		return ResponseEntity.ok(atms);
	}
	
	@GetMapping("/city")
	public ResponseEntity<AtmList> listAtmsByAddress(@RequestParam String city) throws BusinessException {
		AtmList atms = service.findByAddressCity(city);
		return ResponseEntity.ok(atms);
	}
	
	@PostMapping
	public ResponseEntity<String> registerAtm(@RequestBody @Valid AtmForm form
			, UriComponentsBuilder uriBuilder) {
		Atm atm = service.register(form);
		URI uri = uriBuilder.buildAndExpand(atm.getId()).toUri();
		return ResponseEntity.created(uri).body(atm.getId());
	}
}