package com.example.votersApp.services;

import com.example.votersApp.cloud.CloudService;
import com.example.votersApp.data.dtos.request.RegisterRequest;
import com.example.votersApp.data.dtos.request.UpdateRequest;
import com.example.votersApp.data.model.AppUser;
import com.example.votersApp.exception.ImageUploadException;
import com.example.votersApp.exception.UserAlreadyExistsException;
import com.example.votersApp.exception.UserNotFoundException;
import com.example.votersApp.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService{
    private final ModelMapper modelMapper;
    private final CloudService cloudService;
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser register(RegisterRequest request) {
        Optional<AppUser> user = appUserRepository.findByEmail(request.getEmail());
        
        if(user.isPresent())throw new UserAlreadyExistsException("User already exists");
        else{
            return registerUser(request);
        }
        
    }

    @Override
    public AppUser updateUser(UpdateRequest request, Long appUserId) {
        Optional<AppUser> foundUser = appUserRepository.findById(appUserId);
        if(foundUser.isEmpty())throw new UserNotFoundException("User does not exists!");
        return update(request, foundUser);
    }

    private AppUser registerUser(RegisterRequest request) {
        AppUser voterDetails = modelMapper.map(request, AppUser.class);
        var imageUrl = cloudService.upload(request.getProfileImage());
        if(imageUrl == null)throw new ImageUploadException("Registration failed!");
        voterDetails.setProfileImage(imageUrl);
        return voterDetails;
    }


    private static AppUser update(UpdateRequest request, Optional<AppUser> foundUser) {
        AppUser user = foundUser.get();
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        return user;
    }
}
