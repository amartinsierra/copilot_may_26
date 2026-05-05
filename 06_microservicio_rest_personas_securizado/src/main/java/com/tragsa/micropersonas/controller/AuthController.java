package com.tragsa.micropersonas.controller;

import com.tragsa.micropersonas.domain.LoginRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${jwt.secret}")
    private String claveSecreta;

    @Value("${jwt.expiration}")
    private Long tiempoExpiracion;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        // Simulación de validación de usuario (En un caso real, consultar BD)
        if ("admin".equals(loginRequest.getUsername()) && "admin".equals(loginRequest.getPassword())) {
            
            String token = Jwts.builder()
                    .subject(loginRequest.getUsername())
                    .claim("authorities", List.of("ROLE_ADMIN", "ROLE_USER"))
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + tiempoExpiracion))
                    .signWith(Keys.hmacShaKeyFor(claveSecreta.getBytes()))
                    .compact();

            return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
