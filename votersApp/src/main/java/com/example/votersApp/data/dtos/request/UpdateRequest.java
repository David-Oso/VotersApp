package com.example.votersApp.data.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateRequest {
    private Long id;
    private String phoneNumber;
    private String email;
}
