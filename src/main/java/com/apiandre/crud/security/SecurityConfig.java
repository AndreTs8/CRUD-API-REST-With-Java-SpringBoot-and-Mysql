
package com.apiandre.crud.security;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


   
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
                .requestMatchers(HttpMethod.GET,"/user").permitAll() // ? Permitir  sin autenticación
                .requestMatchers(HttpMethod.GET,"/auth/login").permitAll() // ? Permitir sin autenticación 
                .requestMatchers(HttpMethod.DELETE, "/user/{id}").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/auth/login").permitAll()
                .requestMatchers(HttpMethod.PUT, "/user/{id}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/auth/login{id}").permitAll()
                .anyRequest().authenticated() // ? Proteger cualquier otra ruta
            )
            .httpBasic(withDefaults()) //  Autenticación básica
            .cors(withDefaults()) // Habilitar CORS con la configuración por defecto
            .build();
    }
    
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500")); // Asegúrate de incluir la URL de tu frontend
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type","x-session-token"));
        config.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    
    
}
