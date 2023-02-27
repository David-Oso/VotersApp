package com.example.votersApp.service;

import com.example.votersApp.data.dto.request.RegisterCandidateRequest;
import com.example.votersApp.data.dto.responses.RegisterResponse;
import com.example.votersApp.data.model.Candidate;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface CandidateService {
    RegisterResponse register(RegisterCandidateRequest candidateRequest);
    Candidate getCandidateById(Long userId);
    Candidate updateCandidate(Long candidateId, JsonPatch updatePayload);
    List<Candidate> getAllCandidate();
    void deleteCandidate(Long id);
    void deleteAllCandidate();
}
