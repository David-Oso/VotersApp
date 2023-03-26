package com.example.votersApp.services;

import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.request.UpdateRequest;
import com.example.votersApp.data.model.AppUser;

public interface AppUserService {
    AppUser register(RegisterRequest request);
    AppUser updateUser(UpdateRequest request, Long appUserId);
}
