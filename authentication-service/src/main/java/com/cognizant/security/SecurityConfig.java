package com.cognizant.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public PasswordEncoder passwordEncoder() {
		LOGGER.info("Start");
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		// httpSecurity.csrf().disable().httpBasic().and().authorizeRequests()
		// .antMatchers("/forget").permitAll()
		// .antMatchers("/users").permitAll()
		// .and()
		// .addFilter(new JwtAuthorizationFilter(authenticationManager()));
		// }
		httpSecurity.csrf().disable().httpBasic().and().authorizeRequests().antMatchers("/forget/**").permitAll()
				.antMatchers("/promote/**").permitAll().antMatchers("/reset/**").permitAll().anyRequest()
				.authenticated().and().addFilter(new JwtAuthorizationFilter(authenticationManager()));
	}
}
