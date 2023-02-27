package com.example.votersApp.data.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterCandidateRequest {
    private String email;
    private String name;
    private String password;
}
