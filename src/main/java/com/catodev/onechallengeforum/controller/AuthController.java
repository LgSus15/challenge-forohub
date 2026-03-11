package com.catodev.onechallengeforum.controller;

import com.catodev.onechallengeforum.dto.auth.JwtTokenDto;
import com.catodev.onechallengeforum.dto.auth.LoginUserDto;
import com.catodev.onechallengeforum.dto.auth.RegisterUserDto;
import com.catodev.onechallengeforum.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Endpoints para registro y login de usuarios")
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario", description = "Permite registrar un nuevo usuario en la base de datos.")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterUserDto dto) {
        authenticationService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica al usuario y devuelve un token JWT.")
    public ResponseEntity<JwtTokenDto> login(@RequestBody @Valid LoginUserDto dto) {
        JwtTokenDto token = authenticationService.login(dto);
        return ResponseEntity.ok(token);
    }
}
