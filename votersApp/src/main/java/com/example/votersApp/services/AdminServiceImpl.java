package com.example.votersApp.services;

import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.response.GetAllResponse;
import com.example.votersApp.data.dtos.response.GetResponse;
import com.example.votersApp.data.dtos.response.RegisterResponse;
import com.example.votersApp.data.model.Admin;
import com.example.votersApp.data.model.AppUser;
import com.example.votersApp.data.model.Candidate;
import com.example.votersApp.data.model.Voter;
import com.example.votersApp.repository.AdminRepository;
import com.example.votersApp.repository.CandidateRepository;
import com.example.votersApp.repository.VoterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService{
    private final AdminRepository adminRepository;
    private final AppUserService appUserService;
    private final VoterService voterService;
    private final VoterRepository voterRepository;
    private final CandidateService candidateService;
    private final CandidateRepository candidateRepository;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        AppUser adminDetails = appUserService.register(request);
        Admin admin = Admin.builder()
                .userDetails(adminDetails)
                .build();

        Admin savedAdmin = adminRepository.save(admin);

        return RegisterResponse.builder()
                .code(HttpStatus.CREATED.value())
                .id(savedAdmin.getId())
                .isSuccessful(true)
                .message("Admin Registration Successful")
                .build();
    }

    @Override
    public void verifyVoterAccount(Long id) {
        Voter voter = voterService.confirmVoter(id);
        voter.getUserDetails().setIsApproved(true);
        voterService.saveVoter(voter);
    }

    @Override
    public void verifyCandidateAccount(Long id) {
        Candidate candidate = candidateService.confirmCandidate(id);
        candidate.getUserDetails().setIsApproved(true);
        candidateService.saveCandidate(candidate);
    }

    @Override
    public Optional<Voter> getVoterById(Long id) {
        return voterRepository.findById(id);
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id) {
        return candidateRepository.findById(id);
    }

    @Override
    public GetAllResponse getAllVoters(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return null;
    }
}
