package com.example.votersApp.repository;

import com.example.votersApp.data.model.AppUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUserDetails, Long> {
}
