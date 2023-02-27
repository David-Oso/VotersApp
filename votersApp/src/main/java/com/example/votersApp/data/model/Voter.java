package com.example.votersApp.data.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long voterId;
    private String phoneNumber;
    @OneToOne(fetch = FetchType.EAGER)
    private Address address;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne(fetch = FetchType.EAGER)
    private AppUserDetails appUserDetails;
    @OneToOne(fetch = FetchType.EAGER)
    private Vote vote;
    private int age;
    private String image;
}
