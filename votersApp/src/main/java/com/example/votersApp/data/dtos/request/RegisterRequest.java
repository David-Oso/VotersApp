package com.example.votersApp.data.dtos.request;

import com.example.votersApp.data.model.Address;
import com.example.votersApp.data.model.Gender;
import com.example.votersApp.data.model.Party;
import com.example.votersApp.data.model.Sit;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private Address address;
    private String nin;
    private String password;
    private MultipartFile profileImage;
    private Sit sit;
    private Party party;
}
