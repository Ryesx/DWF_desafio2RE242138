package sv.edu.udb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.dto.AuthRequest;
import sv.edu.udb.controller.dto.AuthResponse;
import sv.edu.udb.controller.dto.RegisterRequest;
import sv.edu.udb.domain.User;
import sv.edu.udb.repository.UserRepository;
import sv.edu.udb.service.JwtService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            var userDetails = (User) authentication.getPrincipal();
            var jwtToken = jwtService.generateToken(userDetails);
            var refreshToken = jwtService.generateRefreshToken(userDetails);

            return ResponseEntity.ok(
                    new AuthResponse(jwtToken, refreshToken)
            );
        }

        throw new UsernameNotFoundException("Credenciales inválidas");
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword())); // Encripta la contraseña
        user.setFirstname(registerRequest.getFirstname());
        user.setLastname(registerRequest.getLastname());
        user.setAge(registerRequest.getAge());

        User savedUser = userRepository.save(user);

        // No devolver la contraseña en la respuesta
        savedUser.setPassword(null);

        return ResponseEntity.ok(savedUser);
    }
}