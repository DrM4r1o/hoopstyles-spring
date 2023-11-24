package com.hoopstyles.hoopstyles.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hoopstyles.hoopstyles.model.UserHoop;
import com.hoopstyles.hoopstyles.repository.UserRepository;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository repositorio;
	
	//Se ejecuta al iniciar sesión
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserHoop user = repositorio.findFirstByEmail(username);
		UserBuilder builder = null;
		
		if (user != null) {
			builder = User.withUsername(username); // Le damos el User
			builder.disabled(false);
			builder.password(user.getPassword()); // Y le damos la contraseña
			builder.authorities(new SimpleGrantedAuthority("ROLE_USER")); // Autoridades por defecto
		}else {
			throw new UsernameNotFoundException("User no encontraod");
		}
		
		return builder.build();
	}

}
