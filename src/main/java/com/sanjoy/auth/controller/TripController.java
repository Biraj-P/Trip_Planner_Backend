package com.sanjoy.auth.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.sanjoy.auth.model.Trip;
import com.sanjoy.auth.model.User;
import com.sanjoy.auth.repository.TripRepository;
import com.sanjoy.auth.repository.UserRepository;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/api/trips")
public class TripController {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public Trip createTrip(@RequestBody Trip trip, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        trip.setCreator(user);
        trip.setStatus("Pending");
        return tripRepository.save(trip);
    }

    @GetMapping("/my")
    public List<Trip> myTrips(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        return tripRepository.findByCreatorId(user.getId());
    }

    @GetMapping("/all")
    public List<Trip> allTrips() {
        return tripRepository.findAll();
    }

    @PostMapping("/{id}/join")
    public Trip joinTrip(@PathVariable Long id, Principal principal) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        User user = userRepository.findByEmail(principal.getName()).orElseThrow();
        trip.getMembers().add(user);
        return tripRepository.save(trip);
    }

    @PostMapping("/{id}/status")
    public Trip approveOrDeny(@PathVariable Long id, @RequestParam String action, Principal principal) {
        Trip trip = tripRepository.findById(id).orElseThrow();
        // Only creator can approve/deny
        if (!trip.getCreator().getEmail().equals(principal.getName())) {
            throw new RuntimeException("Unauthorized");
        }
        if ("approve".equalsIgnoreCase(action)) {
            trip.setStatus("Approved");
        } else {
            trip.setStatus("Denied");
        }
        return tripRepository.save(trip);
    }
}

