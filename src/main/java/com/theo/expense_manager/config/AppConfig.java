package com.theo.expense_manager.config;

import com.theo.expense_manager.security.JwtAuthFilter;
import com.theo.expense_manager.service.JwtService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class AppConfig {

    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService,
                                       @Qualifier("customUserDetailsService")UserDetailsService userDetailsService){
        return  new JwtAuthFilter(jwtService,userDetailsService);
    }
}
