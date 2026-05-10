package com.example.restau.controller;

import com.example.restau.config.JwtUtils;
import com.example.restau.dto.request.LoginRequest;
import com.example.restau.dto.request.RegisterRequest;
import com.example.restau.dto.response.LoginResponse;
import com.example.restau.dto.response.MessageResponse;
import com.example.restau.model.User;
import com.example.restau.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken((User) authentication.getPrincipal());
        User user = (User) authentication.getPrincipal();

        return ResponseEntity.ok(LoginResponse.builder()
                .token(jwt)
                .id(user.getId())
                .email(user.getEmail())
                .nomComplet(user.getNomComplet())
                .role(user.getRole().name())
                .build());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok(MessageResponse.builder()
                .message("Inscription réussie !")
                .success(true)
                .build());
    }
}
