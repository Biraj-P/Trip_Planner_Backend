package com.sanjoy.auth.service;

import com.sanjoy.auth.dto.ProfileRequest;
import com.sanjoy.auth.dto.ProfileResponse;
import com.sanjoy.auth.model.User;
import com.sanjoy.auth.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get user profile by email
     */
    public ProfileResponse getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ProfileResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPicture(),
                user.getAge(),
                user.getGender(),
                user.getPhoneNumber(),
                user.getCollege(),
                user.getCourse(),
                user.getGraduationYear(),
                user.getLocation(),
                user.getBio()
        );
    }

    /**
     * Update user profile
     */
    @Transactional
    public ProfileResponse updateUserProfile(String email, ProfileRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update only the fields that are provided
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        if (request.getGender() != null && !request.getGender().isEmpty()) {
            user.setGender(request.getGender());
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getCollege() != null && !request.getCollege().isEmpty()) {
            user.setCollege(request.getCollege());
        }
        if (request.getCourse() != null && !request.getCourse().isEmpty()) {
            user.setCourse(request.getCourse());
        }
        if (request.getGraduationYear() != null) {
            user.setGraduationYear(request.getGraduationYear());
        }
        if (request.getLocation() != null) {
            user.setLocation(request.getLocation());
        }
        if (request.getBio() != null && !request.getBio().isEmpty()) {
            user.setBio(request.getBio());
        }

        User savedUser = userRepository.save(user);

        return new ProfileResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPicture(),
                savedUser.getAge(),
                savedUser.getGender(),
                savedUser.getPhoneNumber(),
                savedUser.getCollege(),
                savedUser.getCourse(),
                savedUser.getGraduationYear(),
                savedUser.getLocation(),
                savedUser.getBio());
    }
}
