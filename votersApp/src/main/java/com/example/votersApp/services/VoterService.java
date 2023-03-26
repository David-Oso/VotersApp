package com.example.votersApp.services;

import com.example.votersApp.data.dtos.request.LoginRequest;
import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.request.UpdateRequest;
import com.example.votersApp.data.dtos.request.VoteRequest;
import com.example.votersApp.data.dtos.response.RegisterResponse;
import com.example.votersApp.data.dtos.response.UpdateResponse;
import com.example.votersApp.data.dtos.response.VoteResponse;
import com.example.votersApp.data.model.Voter;

public interface VoterService {
    RegisterResponse register(RegisterRequest request);
    Voter login(LoginRequest request);
    UpdateResponse updateVoter(UpdateRequest request);
    VoteResponse voter(VoteRequest request);
    Voter confirmVoter(Long id);
    void saveVoter(Voter voter);
}
