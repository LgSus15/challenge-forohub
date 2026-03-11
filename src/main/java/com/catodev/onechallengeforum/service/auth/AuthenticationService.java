package com.catodev.onechallengeforum.service.auth;

import com.catodev.onechallengeforum.dto.auth.JwtTokenDto;
import com.catodev.onechallengeforum.dto.auth.LoginUserDto;
import com.catodev.onechallengeforum.dto.auth.RegisterUserDto;
import com.catodev.onechallengeforum.model.Profile;
import com.catodev.onechallengeforum.model.User;
import com.catodev.onechallengeforum.repository.ProfileRepository;
import com.catodev.onechallengeforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Transactional
    public void register(RegisterUserDto dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("El email ya está registrado");
        }

        Profile profile = profileRepository.findByName("ROLE_STUDENT")
                .orElseThrow(() -> new RuntimeException("Error: Rol STUDENT no encontrado"));

        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setProfile(profile);

        userRepository.save(user);
    }

    public JwtTokenDto login(LoginUserDto dto) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken((User) Objects.requireNonNull(usuarioAutenticado.getPrincipal()));
        return new JwtTokenDto(token);
    }
}
