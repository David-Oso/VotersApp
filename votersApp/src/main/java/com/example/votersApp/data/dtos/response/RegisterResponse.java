package com.example.votersApp.data.dtos.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    private Long id;
    private String message;
    private int code;
    private boolean isSuccessful;
}
