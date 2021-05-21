package br.ufc.crateus.npds.events.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;

@Service
public class JwtUserDetailsService implements UserDetailsService {

//	@Autowired
//    private UserService userService;
	
	class UserSystem {
		private String username;
		private String password;
		
		UserSystem(String username){
			this.username = username;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		 
		UserSystem user = new UserSystem(email); 
		
		// ATENÇÃO: o User é uma classe do pacote SpringFramework > Security > UserDetails
		return new User(user.getUsername(), "1234", new ArrayList<>());
	}
}
