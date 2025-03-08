
package com.apiandre.crud.controllers;

import com.apiandre.crud.dto.LoginRequest;
import com.apiandre.crud.services.AuthService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
                boolean authenticated = authService.authenticateUser(request);

                Map<String, String> response = new HashMap<>();
                if (authenticated) {
                    response.put("message", "Login exitoso");
                    return ResponseEntity.ok(response);
                } else {
                    response.put("error", "Credenciales incorrectas");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
                }
        }
}