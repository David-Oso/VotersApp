package com.example.votersApp.data.dtos.request;

import com.example.votersApp.data.model.Party;
import com.example.votersApp.data.model.Sit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VoteRequest {
    private Long candidateId;
    private Sit candidateSit;
    private Party candidateParty;
}
