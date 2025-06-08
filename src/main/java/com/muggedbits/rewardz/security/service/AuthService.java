package com.muggedbits.rewardz.security.service;

import com.muggedbits.rewardz.security.AuthRequest;
import com.muggedbits.rewardz.user.model.User;
import com.muggedbits.rewardz.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean register(AuthRequest authRequest) {
        if (userRepository.findByEmail(authRequest.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists with email: " + authRequest.getEmail());
        }

        User user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .role("USER") // Default role, can be changed later
                .build();
        userRepository.save(user);
        return true;
    }
}
