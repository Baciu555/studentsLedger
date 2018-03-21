package com.baciu.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.baciu.dto.AdministratorDTO;
import com.baciu.entity.Administrator;

public class AdministratorConverter implements SimpleConverter<Administrator, AdministratorDTO> {

	@Override
	public AdministratorDTO toDTO(Administrator source) {
		AdministratorDTO administratorDTO = AdministratorDTO.builder()
				.id(source.getId())
				.name(source.getName())
				.surname(source.getSurname())
				.email(source.getEmail())
				.password(source.getPassword()).build();
		
		return administratorDTO;
	}

	@Override
	public Administrator toEntity(AdministratorDTO source) {
		Administrator administrator = Administrator.builder()
				.id(source.getId())
				.name(source.getName())
				.surname(source.getSurname())
				.email(source.getEmail())
				.password(source.getPassword()).build();
		
		return administrator;
	}

	@Override
	public Iterable<AdministratorDTO> toDTO(Iterable<Administrator> source) {
		List<Administrator> administrator = (List<Administrator>) source;
		return administrator.stream().map(a -> toDTO(a)).collect(Collectors.toList());
	}

	@Override
	public Iterable<Administrator> toEntity(Iterable<AdministratorDTO> source) {
		List<AdministratorDTO> administrator = (List<AdministratorDTO>) source;
		return administrator.stream().map(a -> toEntity(a)).collect(Collectors.toList());
	}

}
