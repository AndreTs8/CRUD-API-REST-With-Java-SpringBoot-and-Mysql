
package com.apiandre.crud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


   
// borrar SecurityConfig , deshabilita el registro sin autenticacion , uso para pruebas con postman
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable()) // ❌ Solo para pruebas, en producción evalúa mantenerlo activado
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/user").permitAll() //  Permitir registro sin autenticación
                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // ? Permitir login sin autenticación
                .anyRequest().authenticated() // ? Proteger cualquier otra ruta
            )
            .httpBasic(withDefaults()) //  Autenticación básica
            .build();
    }
    
    
}
