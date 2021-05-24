package br.ufc.crateus.npds.events.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {


	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		
		// ATENÇÃO: o User é uma classe do pacote SpringFramework > Security > UserDetails
		return new User("admin", "admin", new ArrayList<>());
	}
}
