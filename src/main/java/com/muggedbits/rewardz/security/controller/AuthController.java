package com.muggedbits.rewardz.security.controller;

import com.muggedbits.rewardz.security.AuthRequest;
import com.muggedbits.rewardz.security.JwtResponse;
import com.muggedbits.rewardz.security.JwtUtil;
import com.muggedbits.rewardz.security.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(), authRequest.getPassword()
                    )
            );

            String token = jwtUtil.generateToken(authRequest.getEmail());
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
            System.out.println("Login failed: " + e.getMessage());
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed" + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.internalServerError().body("Login failed");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        authService.register(authRequest);
        return ResponseEntity.ok("User registered successfully");
    }
}
