package com.portifolyo.airlinesystem.config;

import com.portifolyo.airlinesystem.entity.User;
import com.portifolyo.airlinesystem.enums.Role;
import com.portifolyo.airlinesystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("Admin123!"))
                    .email("admin@airline.com")
                    .fullName("Sistem Yöneticisi")
                    .role(Role.ROLE_ADMIN)
                    .build();
            userRepository.save(admin);
            System.out.println(">>> Varsayilan admin kullanici olusturuldu -> username: admin / password: Admin123!");
        }
    }
}