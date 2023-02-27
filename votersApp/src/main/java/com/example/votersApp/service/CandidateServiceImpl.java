package com.example.votersApp.service;

import com.example.votersApp.data.dto.request.RegisterCandidateRequest;
import com.example.votersApp.data.dto.responses.RegisterResponse;
import com.example.votersApp.data.model.AppUserDetails;
import com.example.votersApp.data.model.Candidate;
import com.example.votersApp.exception.BusinessLogicException;
import com.example.votersApp.repository.CandidateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CandidateServiceImpl implements CandidateService{
    private final CandidateRepository candidateRepository;
//    private CloudService cloudService;
    private final ModelMapper modelMapper;

    @Override
    public RegisterResponse register(RegisterCandidateRequest candidateRequest) {
        AppUserDetails candidateDetails = modelMapper.map(candidateRequest, AppUserDetails.class);
        candidateDetails.setCreatedAt(LocalDateTime.now().toString());

        Candidate candidate = Candidate.builder()
//                .appUserDetails(candidateDetails)
                .build();

        Candidate saveCandidate = candidateRepository.save(candidate);

        return RegisterResponse.builder()
                .codeStatus(HttpStatus.CREATED.value())
                .id(saveCandidate.getId())
                .isRegistered(true)
                .message("Candidate Registration Successful")
                .build();
    }

    @Override
    public Candidate getCandidateById(Long userId) {
        return candidateRepository.findById(userId).orElseThrow(()->
                new BusinessLogicException(
                        String.format("Candidate with id %d not found...", userId)
                ));
    }

    @Override
    public Candidate updateCandidate(Long candidateId, JsonPatch updatePayload) {
        ObjectMapper mapper = new ObjectMapper();
        Candidate foundCandidate = getCandidateById(candidateId);
        JsonNode node = mapper.convertValue(foundCandidate, JsonNode.class);

        try{
        JsonNode updatedNode = updatePayload.apply(node);

        var updateCandidate = mapper.convertValue(updatedNode, Candidate.class);
        updateCandidate = candidateRepository.save(updateCandidate);
        return updateCandidate;
    } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }
    }

        @Override
    public List<Candidate> getAllCandidate() {
        return candidateRepository.findAll();
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    @Override
    public void deleteAllCandidate() {
        candidateRepository.deleteAll();
    }

}
