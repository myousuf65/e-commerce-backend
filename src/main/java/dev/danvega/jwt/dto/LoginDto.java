package dev.danvega.jwt.dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
