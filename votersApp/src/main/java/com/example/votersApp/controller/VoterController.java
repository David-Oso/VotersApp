package com.example.votersApp.controller;

import com.example.votersApp.data.dto.request.RegisterVoterRequest;
import com.example.votersApp.data.dto.responses.RegisterResponse;
import com.example.votersApp.service.VoterService;
import com.github.fge.jsonpatch.JsonPatch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/voter")
@AllArgsConstructor
public class VoterController {
    private final VoterService voterService;

    @PostMapping("/register")
    public ResponseEntity<?> registerVoter(@RequestBody RegisterVoterRequest registerVoterRequest){
        RegisterResponse registerResponse = voterService.registerVoter(registerVoterRequest);
        return ResponseEntity.status(registerResponse.getCodeStatus()).body(registerResponse);
    }
    @GetMapping("/{voterId}")
    public ResponseEntity<?> getVoterById(@PathVariable Long voterId){
        var foundVoter = voterService.getVoterById(voterId);
        return ResponseEntity.status(HttpStatus.OK).body(foundVoter);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVoters(){
        var response = voterService.getAllVoter();
        return ResponseEntity.ok(response);
    }
    @PatchMapping(value = "/{voterId}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateVoter(@PathVariable Long voterId, @RequestBody JsonPatch updatePatch){
        try{
            var response = voterService.updateVoter(voterId, updatePatch);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
    @DeleteMapping("/{voterId}")
    public ResponseEntity<?> deleteVoter(@PathVariable Long voterId){
        voterService.deleteVoterById(voterId);
        return ResponseEntity.ok("Voter deleted successfully...");
    }
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAllVoter(){
        voterService.deleteAllVoter();;
        return ResponseEntity.ok("All voters deleted successfully...");
    }
}
