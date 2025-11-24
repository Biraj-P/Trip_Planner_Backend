package com.sanjoy.auth.controller;

import com.sanjoy.auth.dto.ProfileRequest;
import com.sanjoy.auth.dto.ProfileResponse;
import com.sanjoy.auth.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return Map.of("error", "Not authenticated");
        }

        Map<String, Object> response = new HashMap<>();

        // Get name (with fallback)
        String name = principal.getAttribute("name");
        String login = principal.getAttribute("login");
        if (name == null || name.isEmpty()) {
            name = login; // GitHub username fallback
        }

        response.put("name", name != null ? name : "Unknown User");

        // Get email (with fallback)
        String email = principal.getAttribute("email");
        response.put("email", email != null ? email : "No email provided");

        // Detect provider based on attributes
        String picture = principal.getAttribute("picture");
        String avatarUrl = principal.getAttribute("avatar_url");
        String provider;

        if (picture != null && avatarUrl == null) {
            // Google provides "picture" but not "avatar_url"
            provider = "Google";
            response.put("picture", picture);
        } else if (avatarUrl != null) {
            // GitHub provides "avatar_url"
            provider = "GitHub";
            response.put("picture", avatarUrl);
            response.put("avatar_url", avatarUrl);
            if (login != null) {
                response.put("username", login);
            }
        } else {
            provider = "Unknown";
            response.put("picture", null);
        }

        response.put("provider", provider);
        return response;
    }

    /**
     * NEW ENDPOINT: Get full user profile
     */
    @GetMapping("/user/profile")
    public ProfileResponse getUserProfile(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            throw new RuntimeException("Not authenticated");
        }

        String email = principal.getAttribute("email");
        if (email == null) {
            String login = principal.getAttribute("login");
            email = login + "@github.local";
        }

        return userService.getUserProfile(email);
    }

    /**
     * NEW ENDPOINT: Update user profile
     */
    @PutMapping("/user/profile")
    public ProfileResponse updateUserProfile(
            @AuthenticationPrincipal OAuth2User principal,
            @RequestBody ProfileRequest request) {

        if (principal == null) {
            throw new RuntimeException("Not authenticated");
        }

        String email = principal.getAttribute("email");
        if (email == null) {
            String login = principal.getAttribute("login");
            email = login + "@github.local";
        }

        return userService.updateUserProfile(email, request);
    }

    @PostMapping("/logout")
    public Map<String, String> customLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return Map.of("message", "Logged out successfully");
    }
}
