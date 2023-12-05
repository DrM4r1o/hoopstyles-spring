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
                .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
				// .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()				
                // .requestMatchers(AntPathRequestMatcher.antMatcher("/filter/**")).permitAll()
                // .requestMatchers(AntPathRequestMatcher.antMatcher("/product/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()				
                .requestMatchers(AntPathRequestMatcher.antMatcher("/icons/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
				.requestMatchers(AntPathRequestMatcher.antMatcher("/auth/**")).permitAll()
				.anyRequest().authenticated()
			)
			.formLogin(login -> login.loginPage("/auth/login")
                                     .usernameParameter("username")
                                     .loginProcessingUrl("/auth/login-post") 
									 .defaultSuccessUrl("/", true) 
									 .permitAll() //
			)
			.logout(logout -> logout
									.logoutUrl("/auth/logout") 
									.logoutSuccessUrl("/")
			
			)
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions( frame -> frame.disable())
			);
									
		
		return http.build();
	}
}

