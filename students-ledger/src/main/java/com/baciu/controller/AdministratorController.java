package com.baciu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.converter.AdministratorConverter;
import com.baciu.dto.AdministratorDTO;
import com.baciu.entity.Administrator;
import com.baciu.exception.AdministratorNotExistsException;
import com.baciu.exception.EmailExistsException;
import com.baciu.service.AdministratorService;

@RestController
public class AdministratorController {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private AdministratorConverter administratorConverter;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("administrator")
	public ResponseEntity<?> getAdministrators() {
		return new ResponseEntity<>(administratorConverter.toDTO(administratorService.getAll()), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("administrator/{id}")
	public ResponseEntity<?> getAdministrator(@PathVariable("id") Long id) {
		Administrator administrator = administratorService.getAdministrator(id);
		if (administrator == null)
			return new ResponseEntity<>("administrator not found", HttpStatus.OK);
		return new ResponseEntity<>(administratorConverter.toDTO(administrator), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("administrator")
	public ResponseEntity<?> addAdministrator(@Valid @RequestBody AdministratorDTO administratorDTO) throws EmailExistsException {
		Administrator administrator = administratorService.addAdministrator(administratorConverter.toEntity(administratorDTO));
		return new ResponseEntity<>(administratorConverter.toDTO(administrator), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("administrator")
	public ResponseEntity<?> updateAdministrator(@Valid @RequestBody AdministratorDTO administratorDTO) throws EmailExistsException {
		Administrator administrator = administratorService.updateAdministrator(administratorConverter.toEntity(administratorDTO));
		return new ResponseEntity<>(administratorConverter.toDTO(administrator), HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("administrator")
	public ResponseEntity<?> deleteAdministrator(@PathVariable("id") Long id) throws AdministratorNotExistsException {
		administratorService.deleteAdministrator(id);
		return new ResponseEntity<>("administrator deleted", HttpStatus.OK);
	}

}
