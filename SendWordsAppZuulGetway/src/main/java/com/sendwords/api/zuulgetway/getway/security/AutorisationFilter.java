package com.sendwords.api.zuulgetway.getway.security;

 

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
 
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

 

public class AutorisationFilter extends BasicAuthenticationFilter {
	Environment environment;

    public AutorisationFilter(AuthenticationManager authManager, Environment environment) {
        super(authManager);
        this.environment = environment;
    }
    
   

}
