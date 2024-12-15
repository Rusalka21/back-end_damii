package com.back.pid_grupo01.service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.back.pid_grupo01.jwt.JwtService;
import com.back.pid_grupo01.model.AuthResponse;
import com.back.pid_grupo01.model.LoginRequest;
import com.back.pid_grupo01.model.RegisterRequest;
import com.back.pid_grupo01.model.RegisterResponse;
import com.back.pid_grupo01.model.Usuario;
import com.back.pid_grupo01.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	private final UsuarioRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user=userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(user);
		Optional<Usuario> usuario = userRepository.findByEmail(request.getEmail());

		return AuthResponse.builder()
				.token(token)
				.id(usuario.get().getId())
				.username(usuario.get().getUsername1())
				.email(usuario.get().getEmail())
				.build();
    }

    public RegisterResponse register(RegisterRequest request) {
    	
    	String usernameSuggestion = "";
    	
    	boolean uniqueDNI = true;
    	boolean uniqueEmail = true;
    	boolean uniqueUsername = true;
    	
    	Optional<Usuario> existingDni = userRepository.findByDni(request.getDni());
    	Optional<Usuario> existingEmail = userRepository.findByEmail(request.getEmail());
    	Optional<Usuario> existingUsername = userRepository.findByUsername(request.getUsername());
    	
    	if(existingDni.isPresent()) {
    		uniqueDNI = !uniqueDNI;
    	}
    	if(existingEmail.isPresent()) {
    		uniqueEmail = !uniqueEmail;
    	}
    	if(existingUsername.isPresent()) {
    		uniqueUsername = !uniqueUsername;
    	}
    	
    	if(uniqueUsername == false) {
    		usernameSuggestion = generateSuggestion(request.getUsername());
    	}    	
    	
    	
    	if(uniqueDNI && uniqueEmail && uniqueUsername) {
            Usuario user = Usuario.builder()
                    .nombre(request.getNombre())
                    .apellido(request.getApellido())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .dni(request.getDni())
                    .username(request.getUsername())
                    .fecha(new Date())
                    .build();

                userRepository.save(user);
                
                return RegisterResponse.builder()
                		.token(jwtService.getToken(user))
                		.isDniUnique(uniqueDNI)
                		.isEmailUnique(uniqueEmail)
                		.isUsernameUnique(uniqueUsername)
                		.suggestion(usernameSuggestion)
                		.build();
    	} else {
    		return RegisterResponse.builder()
            		.token("")
            		.isDniUnique(uniqueDNI)
            		.isEmailUnique(uniqueEmail)
            		.isUsernameUnique(uniqueUsername)
            		.suggestion(usernameSuggestion)
            		.build();
    	}   
        
    }
    
    private String generateSuggestion(String base) {
        String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder suggestion = new StringBuilder(base);

        for (int i = 0; i < 4; i++) {
            suggestion.append(characters.charAt(random.nextInt(characters.length())));
        }

        return suggestion.toString();
    }
}
