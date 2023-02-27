package com.example.votersApp.service;

import com.example.votersApp.data.dto.request.RegisterVoterRequest;
import com.example.votersApp.data.dto.responses.RegisterResponse;
import com.example.votersApp.data.model.Voter;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.List;

public interface VoterService {
    RegisterResponse registerVoter(RegisterVoterRequest VoterRequest);
    Voter getVoterById(Long voterId);
    Voter updateVoter(Long voterId, JsonPatch updatePayload);
    List<Voter> getAllVoter();
    void deleteVoterById(Long voterId);
    void deleteAllVoter();
}
