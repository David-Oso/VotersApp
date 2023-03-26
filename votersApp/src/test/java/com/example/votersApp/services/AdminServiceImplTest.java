package com.example.votersApp.services;

import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.response.RegisterResponse;
import com.example.votersApp.data.model.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class AdminServiceImplTest {
    @Autowired
    AdminService adminService;

    private RegisterRequest request;
    @BeforeEach
    void setUp() {
    }

    @Test
    void register() {
    }

    @Test
    void verifyVoterAccount() {
    }

    @Test
    void verifyCandidateAccount() {
    }
}