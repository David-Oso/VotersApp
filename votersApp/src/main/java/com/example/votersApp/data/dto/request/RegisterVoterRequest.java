package com.example.votersApp.data.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterVoterRequest {
    private String name;
    private String email;
    private String password;
}
