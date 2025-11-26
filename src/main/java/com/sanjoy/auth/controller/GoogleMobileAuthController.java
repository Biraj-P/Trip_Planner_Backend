package com.sanjoy.auth.controller;

import com.sanjoy.auth.dto.GoogleLogInRequest;
import com.sanjoy.auth.service.GoogleTokenVerifierService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.sanjoy.auth.dto.ProfileResponse;
import com.sanjoy.auth.model.User;
import com.sanjoy.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class GoogleMobileAuthController {

    private final GoogleTokenVerifierService verifierService;
    private final UserService userService;

    public GoogleMobileAuthController(GoogleTokenVerifierService verifierService,
                                      UserService userService) {
        this.verifierService = verifierService;
        this.userService = userService;
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody GoogleLogInRequest request) {

        GoogleIdToken.Payload payload = verifierService.verify(request.getIdToken());

        if (payload == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid ID token");
        }

        String email = payload.getEmail();
        String name = (String) payload.get("name");
        String picture = (String) payload.get("picture");

        User user = userService.findOrCreateGoogleUser(email, name, picture);

        ProfileResponse profile = new ProfileResponse(
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
                user.getBio(),
                user.getLocation()
        );

        return ResponseEntity.ok(profile);
    }
}
