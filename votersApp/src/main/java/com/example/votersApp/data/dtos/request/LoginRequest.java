package com.example.votersApp.data.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private Long id;
    private String password;
}
