package com.example.votersApp.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Address address;
    private String nin;
    private String profileImage;
    private String password;
    private Boolean isApproved;
}
