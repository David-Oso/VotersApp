package com.example.votersApp.services;

import com.example.votersApp.cloud.CloudService;
import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.request.UpdateRequest;
import com.example.votersApp.data.dtos.response.RegisterResponse;
import com.example.votersApp.data.dtos.response.UpdateResponse;
import com.example.votersApp.data.model.AppUser;
import com.example.votersApp.data.model.Candidate;
import com.example.votersApp.exception.UserNotFoundException;
import com.example.votersApp.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService{
    private final CandidateRepository candidateRepository;
    private final AppUserService appUserService;
    private final CloudService cloudService;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        AppUser candidateDetails = appUserService.register(request);
        Candidate candidate =  getCandidate(request, candidateDetails);
        Candidate savedCandidate = candidateRepository.save(candidate);
        return registerCandidate(savedCandidate);
    }


    @Override
    public UpdateResponse update(UpdateRequest request) {
        Candidate candidate = confirmCandidate(request.getId());
        Long id = candidate.getUserDetails().getId();
        AppUser updateUser = appUserService.updateUser(request, id);
        candidate.setUserDetails(updateUser);

        candidateRepository.save(candidate);
        return new UpdateResponse("Update successful");
    }

    @Override
    public Candidate confirmCandidate(Long id) {
        Optional<Candidate> foundCandidate = candidateRepository.findById(id);
        if(foundCandidate.isEmpty()) throw new UserNotFoundException("User not found");
        else return foundCandidate.get();
    }

    @Override
    public void saveCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    private static Candidate  getCandidate(RegisterRequest request, AppUser candidateDetails) {
        return Candidate.builder()
                .userDetails(candidateDetails)
                .sit(request.getSit())
                .party(request.getParty())
                .build();
    }

    private static RegisterResponse registerCandidate(Candidate savedCandidate) {
        return RegisterResponse.builder()
                .code(HttpStatus.CREATED.value())
                .id(savedCandidate.getId())
                .isSuccessful(true)
                .message("Candidate Registration Successful")
                .build();
    }

}
