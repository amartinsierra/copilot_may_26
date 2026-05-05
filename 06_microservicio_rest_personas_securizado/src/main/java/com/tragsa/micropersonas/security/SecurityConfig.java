package com.tragsa.micropersonas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }
    //añade un método para generar tres usurios en memoria con sus roles correspondientes, por ejemplo admin con rol ADMIN y user con rol USER
    //sustituye el método deprecado por builder
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("{noop}password")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}password")
                .roles("ADMIN")
                .build();
        UserDetails guest = User.builder()
                .username("guest")
                .password("{noop}password")
                .roles("GUEST")
                .build();
        return new InMemoryUserDetailsManager(user, admin, guest);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para APIs REST
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin estado
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**").permitAll() // Permitir consola H2
                    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll() // Permitir Swagger
                    .requestMatchers("/auth/**").permitAll() // Permitir login
                    //la operacion de crear persona solo para admin en modo POST
                    .requestMatchers(HttpMethod.POST, "/personas").hasRole("ADMIN") // Solo admin puede crear personas
                    
                    .anyRequest().authenticated() // El resto requiere autenticación
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Necesario para consola H2
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}