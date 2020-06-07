package com.sendwords.api.zuulgetway.getway.security;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	
	

	private Environment environment;

	
	 
	@Autowired
	public WebSecurity(Environment environment) {	 
		this.environment=environment;
		
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests()
	//	.antMatchers(environment.getProperty("api.rh2console.url.path")).permitAll() je le ferai pour l acces au console db
		.antMatchers(HttpMethod.POST,environment.getProperty("api.registration.url.path")).permitAll()
		.antMatchers(HttpMethod.POST,environment.getProperty("api.login.url.path")).permitAll()
		.antMatchers(HttpMethod.POST,environment.getProperty("api.login.url.sendMessage")).permitAll()
		.antMatchers(HttpMethod.GET,environment.getProperty("api.login.url.findAll")).permitAll()
		.antMatchers(HttpMethod.GET,environment.getProperty("api.login.url.findMessage")).permitAll()
        .anyRequest().authenticated()
		.and()
		.addFilter(new AutorisationFilter(authenticationManager(), environment));
			
		      
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	

}
