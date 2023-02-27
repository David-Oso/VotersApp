package com.example.votersApp.data.dto.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    private Long id;
    private String message;
    private int codeStatus;
    private Boolean isRegistered;
}
