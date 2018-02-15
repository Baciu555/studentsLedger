package com.baciu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//	@Autowired
//	private CustomDetailsService customDetailsService;
	
	@Autowired
	public void coinfigureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("baciu").password("123").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("krasnal").password("321").roles("USER");
	}
	
//	@Autowired
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customDetailsService);
//	}
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
	   	.antMatchers("/students/**").hasRole("ADMIN")
	   	.and().httpBasic()
	   	.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 }

}
