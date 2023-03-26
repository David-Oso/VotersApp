package com.example.votersApp.data.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String nin;
    private String ped;
    private Boolean isApproved;
}
