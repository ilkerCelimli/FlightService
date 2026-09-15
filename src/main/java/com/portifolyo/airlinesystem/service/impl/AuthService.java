package com.portifolyo.airlinesystem.service.impl;

import com.portifolyo.airlinesystem.dto.Request.LoginRequest;
import com.portifolyo.airlinesystem.dto.Request.RegisterRequest;
import com.portifolyo.airlinesystem.dto.Response.JwtResponse;
import com.portifolyo.airlinesystem.entity.User;
import com.portifolyo.airlinesystem.enums.Role;
import com.portifolyo.airlinesystem.exception.BadRequestException;
import com.portifolyo.airlinesystem.repository.UserRepository;
import com.portifolyo.airlinesystem.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends BaseServiceImpl<User> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public User register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Bu kullanıcı adı zaten kullanılıyor");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Bu e-posta zaten kayıtlı");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.ROLE_CUSTOMER)
                .build();

        return userRepository.save(user);
    }

    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.generateToken(authentication);

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadRequestException("Kullanıcı bulunamadı"));

        return new JwtResponse(token, user.getId(), user.getUsername(), user.getRole());
    }
}
