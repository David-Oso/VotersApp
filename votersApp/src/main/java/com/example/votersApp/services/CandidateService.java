package com.example.votersApp.services;

import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.request.UpdateRequest;
import com.example.votersApp.data.dtos.response.RegisterResponse;
import com.example.votersApp.data.dtos.response.UpdateResponse;
import com.example.votersApp.data.model.Candidate;

public interface CandidateService {
    RegisterResponse register(RegisterRequest request);
    UpdateResponse update(UpdateRequest request);
    Candidate confirmCandidate(Long id);
    void saveCandidate(Candidate candidate);
}
