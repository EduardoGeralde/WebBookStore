package com.eduardoportfolio.store.conf;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author Eduardo Geralde Neto..
 *
 */

public class Password {

	public static void main(String[] args) {
		
		String password1 = "123";
		String password2 = "456";
		String password3 = "789";
		
		BCryptPasswordEncoder passwordBCrypt = new BCryptPasswordEncoder();
		
		System.out.println(passwordBCrypt.encode(password1));
		System.out.println(passwordBCrypt.encode(password2));
		System.out.println(passwordBCrypt.encode(password3));
	}
}
