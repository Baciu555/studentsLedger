package com.baciu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.baciu.entity.Administrator;
import com.baciu.exception.AdministratorNotExistsException;
import com.baciu.exception.EmailExistsException;
import com.baciu.repository.AdministratorRepository;

@Service
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Iterable<Administrator> getAll() {
		return administratorRepository.findAll();
	}
	
	public Administrator getAdministrator(Long id) {
		if (!administratorRepository.exists(id))
			return null;
		
		return administratorRepository.findOne(id);
	}
	
	public Administrator addAdministrator(Administrator administrator) throws EmailExistsException {
		if (administratorRepository.findByEmail(administrator.getEmail()) != null)
			throw new EmailExistsException();
		
		administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
		
		return administratorRepository.save(administrator);
	}
	
	public Administrator updateAdministrator(Administrator administrator) throws EmailExistsException {
		Administrator existedAdministrator = administratorRepository.findOne(administrator.getId());
		
		if (administratorRepository.findByEmail(administrator.getEmail()) != null 
				&& !administrator.getEmail().equals(existedAdministrator.getEmail()))
			throw new EmailExistsException();
		
		existedAdministrator.setName(administrator.getName());
		existedAdministrator.setSurname(administrator.getSurname());
		existedAdministrator.setEmail(administrator.getEmail());
		existedAdministrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
		
		return administratorRepository.save(existedAdministrator);
	}
	
	public void deleteAdministrator(Long id) throws AdministratorNotExistsException {
		Administrator administrator = administratorRepository.findOne(id);
		if (administrator == null) throw new AdministratorNotExistsException();
		administratorRepository.delete(id);
	}

}
