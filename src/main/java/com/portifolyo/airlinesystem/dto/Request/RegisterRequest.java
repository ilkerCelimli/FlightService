package com.portifolyo.airlinesystem.dto.Request;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {


    @NotBlank(message = "Kullanıcı adı boş olamaz")
    @Size(min = 3, max = 50, message = "Kullanıcı adı 3-50 karakter arasında olmalı")
    private String username;

    @NotBlank(message = "Şifre boş olamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    private String password;

    @NotBlank(message = "E-posta boş olamaz")
    @Email(message = "Geçerli bir e-posta girin")
    private String email;

    @NotBlank(message = "Ad soyad boş olamaz")
    private String fullName;

    private String phoneNumber;
}
