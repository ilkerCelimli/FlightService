package com.portifolyo.airlinesystem.api;

import com.portifolyo.airlinesystem.dto.Request.LoginRequest;
import com.portifolyo.airlinesystem.dto.Request.RegisterRequest;
import com.portifolyo.airlinesystem.dto.Response.ApiResponse;
import com.portifolyo.airlinesystem.dto.Response.JwtResponse;
import com.portifolyo.airlinesystem.entity.User;
import com.portifolyo.airlinesystem.service.impl.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthApi {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("Kayıt başarılı", user.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@Valid @RequestBody LoginRequest request) {
        JwtResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Giriş başarılı", response));
    }
}
