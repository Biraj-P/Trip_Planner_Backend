package com.sanjoy.auth.config;

import com.sanjoy.auth.model.User;
import com.sanjoy.auth.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Get the provider name (google or github)
        String provider = userRequest.getClientRegistration().getRegistrationId();

        // Extract user details based on provider - use FINAL variables
        final String name;
        final String email;
        final String picture;

        if ("github".equals(provider)) {
            // GitHub specific attributes
            name = oAuth2User.getAttribute("name");
            String githubEmail = oAuth2User.getAttribute("email");
            picture = oAuth2User.getAttribute("avatar_url");

            // GitHub may not return email if not public
            if (githubEmail == null || githubEmail.isEmpty()) {
                email = oAuth2User.getAttribute("login") + "@github.local";
            } else {
                email = githubEmail;
            }
        } else {
            // Google specific attributes (default)
            System.out.println(oAuth2User);
            name = oAuth2User.getAttribute("name");
            email = oAuth2User.getAttribute("email");
            picture = oAuth2User.getAttribute("picture");
        }

        // Now name, email, and picture are effectively final and can be used in lambda
        userRepository.findByEmail(email)
                .map(user -> {
                    user.setName(name);
                    user.setPicture(picture);
                    return userRepository.save(user);
                })
                .orElseGet(() ->
                        userRepository.save(new User(name, email, picture))
                );

        return oAuth2User;
    }
}
