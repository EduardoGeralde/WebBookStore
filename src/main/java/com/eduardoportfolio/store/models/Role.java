package com.eduardoportfolio.store.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author Eduardo Geralde Neto
 * 
 * 
 */

@Entity
public class Role implements GrantedAuthority {
	
	@Id
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}
}