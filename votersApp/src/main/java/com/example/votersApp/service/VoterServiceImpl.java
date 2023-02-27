package com.example.votersApp.service;

import com.example.votersApp.data.dto.request.RegisterVoterRequest;
import com.example.votersApp.data.dto.responses.RegisterResponse;
import com.example.votersApp.data.model.AppUserDetails;
import com.example.votersApp.data.model.Voter;
import com.example.votersApp.exception.BusinessLogicException;
import com.example.votersApp.repository.VoterRepository;
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
public class VoterServiceImpl implements VoterService {
    private final VoterRepository voterRepository;
    private final ModelMapper modelMapper;

    @Override
    public RegisterResponse registerVoter(RegisterVoterRequest voterRequest) {
        AppUserDetails userDetails  = modelMapper.map(voterRequest, AppUserDetails.class);
        userDetails.setCreatedAt(LocalDateTime.now().toString());

        Voter voter = Voter.builder()
//                .appUserDetails(userDetails)
                .build();

        Voter savedVoter = voterRepository.save(voter);


        return RegisterResponse.builder()
                .codeStatus(HttpStatus.CREATED.value())
                .id(savedVoter.getVoterId())
                .isRegistered(true)
                .message("Voter Registration successful")
                .build();
    }

    @Override
    public Voter getVoterById(Long voterId) {
        return voterRepository.findById(voterId).orElseThrow(()->
                new BusinessLogicException(
                        String.format("Voter with id %d not found", voterId)
                ));
    }

    @Override
    public Voter updateVoter(Long voterId, JsonPatch updatePayload) {
        ObjectMapper mapper = new ObjectMapper();
        Voter foundVoter = getVoterById(voterId);

        JsonNode node = mapper.convertValue(foundVoter, JsonNode.class);
        try{
            JsonNode updateNode = updatePayload.apply(node);
             var updateVoter = mapper.convertValue(updateNode, Voter.class);
             updateVoter = voterRepository.save(updateVoter);
             return updateVoter;
        } catch (JsonPatchException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Voter> getAllVoter() {
        return voterRepository.findAll();
    }

    @Override
    public void deleteVoterById(Long voterId) {
        voterRepository.deleteById(voterId);
    }

    @Override
    public void deleteAllVoter() {
        voterRepository.deleteAll();
    }
}
