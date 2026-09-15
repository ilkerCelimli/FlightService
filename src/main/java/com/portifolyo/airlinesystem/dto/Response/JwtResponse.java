package com.portifolyo.airlinesystem.dto.Response;

import com.portifolyo.airlinesystem.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String userId;
    private String username;
    private Role role;
}
