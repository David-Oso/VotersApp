package com.example.votersApp.controller;

import com.example.votersApp.data.dto.request.RegisterCandidateRequest;
import com.example.votersApp.data.dto.responses.RegisterResponse;
import com.example.votersApp.service.CandidateService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/candidate")
@AllArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterCandidateRequest registerCandidateRequest){
        RegisterResponse registerResponse = candidateService.register(registerCandidateRequest);
        return ResponseEntity.status(registerResponse.getCodeStatus()).body(registerResponse);
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<?> getCandidateById(@PathVariable Long candidateId){
        var foundCandidate = candidateService.getCandidateById(candidateId);
        return ResponseEntity.status(HttpStatus.OK).body(foundCandidate);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllCandidate(){
        var response = candidateService.getAllCandidate();
        return ResponseEntity.ok(response);
    }
    @PatchMapping(value = "{candidateId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateCandidate(@PathVariable Long candidateId, @RequestBody JsonPatch updatePatch){
        try{
        var response = candidateService.updateCandidate(candidateId, updatePatch);
        return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
    @DeleteMapping("{candidateId}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long candidateId){
        candidateService.deleteCandidate(candidateId);
        return ResponseEntity.ok("Candidate deleted successfully...");
    }
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllCandidate(){
        candidateService.deleteAllCandidate();
        return ResponseEntity.ok("All candidate deleted successfully...");
    }
}
