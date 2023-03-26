package com.example.votersApp.repository;

import com.example.votersApp.data.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter,Long> {
}
