package com.example.votersApp.data.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @OneToOne
    private Address address;
    private String image;
    private int age;
    private String party;
    private ElectionType electionType;
    private Long voteCount;
    @OneToOne(cascade = CascadeType.PERSIST)
    private AppUserDetails appUserDetails;
}
