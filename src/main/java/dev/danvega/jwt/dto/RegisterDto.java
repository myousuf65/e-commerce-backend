package dev.danvega.jwt.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
}
