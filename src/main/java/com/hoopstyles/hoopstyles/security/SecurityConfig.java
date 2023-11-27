package com.hoopstyles.hoopstyles.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
    protected BCryptPasswordEncoder getPassWordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	@Bean
	protected DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(getPassWordEncoder());
		return authProvider;
	}

	@Bean
	protected SecurityFilterChain filter(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
				.requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/public/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/auth/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/files/**")).permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(login -> login.loginPage("/auth/login") //Página por defecto login
                                     .usernameParameter("username") //Nombre del campo del formulario
                                     .loginProcessingUrl("/auth/login-post") // Controlador que manejará la autenticación. Lo implementa Spring Security
									 .defaultSuccessUrl("/", true) //Si el login es correcto, página por defecto
									 .permitAll() //
			)
			.logout(logout -> logout
									.logoutUrl("/auth/logout") // Cuanto mandemos al usuario a esta ruta en POST se deslogueará automáticamente
									.logoutSuccessUrl("/")	//Cuando se desloguee
			
			)
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions( frame -> frame.disable())
			);
									
		
		return http.build();
	}
}

