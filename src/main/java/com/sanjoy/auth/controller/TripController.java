package com.sanjoy.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import com.sanjoy.auth.model.Trip;
import com.sanjoy.auth.model.User;
import com.sanjoy.auth.repository.TripRepository;
import com.sanjoy.auth.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public Trip createTrip(@RequestBody Trip trip, @AuthenticationPrincipal OAuth2User principal) {
        String email = getEmailFromPrincipal(principal);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        trip.setCreator(user);
        trip.setStatus("Pending");
        return tripRepository.save(trip);
    }

    @GetMapping("/my")
    public List<Trip> myTrips(@AuthenticationPrincipal OAuth2User principal) {
        String email = getEmailFromPrincipal(principal);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        return tripRepository.findByCreatorId(user.getId());
    }

    @GetMapping("/all")
    public List<Trip> allTrips() {
        return tripRepository.findAll();
    }

    @PostMapping("/{id}/join")
    public Trip joinTrip(@PathVariable Long id, @AuthenticationPrincipal OAuth2User principal) {
        String email = getEmailFromPrincipal(principal);
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        trip.getMembers().add(user);
        return tripRepository.save(trip);
    }

    @PostMapping("/{id}/status")
    public Trip approveOrDeny(@PathVariable Long id, @RequestParam String action,
                              @AuthenticationPrincipal OAuth2User principal) {
        String email = getEmailFromPrincipal(principal);
        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        // Only creator can approve/deny
        if (!trip.getCreator().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized: Only trip creator can approve/deny");
        }

        if ("approve".equalsIgnoreCase(action)) {
            trip.setStatus("Approved");
        } else {
            trip.setStatus("Denied");
        }

        return tripRepository.save(trip);
    }

    // Helper method to extract email from OAuth2User (works for both Google and GitHub)
    private String getEmailFromPrincipal(OAuth2User principal) {
        String email = principal.getAttribute("email");

        // If email is null (GitHub sometimes doesn't provide email), fallback to login
        if (email == null || email.isEmpty()) {
            String login = principal.getAttribute("login");
            if (login != null) {
                email = login + "@github.local"; // GitHub fallback
            }
        }

        if (email == null) {
            throw new RuntimeException("Could not extract email from OAuth2 principal");
        }

        return email;
    }
}
