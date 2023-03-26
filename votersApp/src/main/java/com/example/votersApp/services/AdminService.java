package com.example.votersApp.services;

import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.response.GetAllResponse;
import com.example.votersApp.data.dtos.response.RegisterResponse;
import com.example.votersApp.data.model.Candidate;
import com.example.votersApp.data.model.Voter;

import java.util.Optional;

public interface AdminService {
    RegisterResponse register(RegisterRequest request);
    void verifyVoterAccount(Long id);
    void verifyCandidateAccount(Long id);
    Optional<Voter> getVoterById(Long id);
    Optional<Candidate> getCandidateById(Long id);
    GetAllResponse getAllVoters(int pageNumber, int pageSize);
}
