
package com.apiandre.crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Service
public class PasswordConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        
        return new BCryptPasswordEncoder();
        
    }
 

}
