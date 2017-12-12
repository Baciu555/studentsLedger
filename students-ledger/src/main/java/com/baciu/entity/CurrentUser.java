package com.baciu.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.baciu.dto.StudentDTO;

public class CurrentUser implements UserDetails {

	
	private static final long serialVersionUID = 1L;
	private StudentDTO user;

	public CurrentUser(StudentDTO student) {
		this.user = student;
	}

	public String getUsername() {
		return user.getEmail();
	}

	public String getPassword() {
		return user.getPassword();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
