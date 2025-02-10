package com.aneesh.usermanagement.controller;

import com.aneesh.usermanagement.model.CustomUserDetails;
import com.aneesh.usermanagement.model.TokenResponse;
import com.aneesh.usermanagement.service.CustomUserDetailsService;
import com.aneesh.usermanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .withZone(ZoneId.of("UTC"));

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestParam String username, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails.getUsername(),userDetails.getUser().getRole().name());
        long expirationTimestamp = jwtUtil.getExpirationTime(token);
        // Convert to readable datetime format (ISO 8601)
        String expiresAt = formatter.format(Instant.ofEpochMilli(expirationTimestamp));
        return ResponseEntity.ok(new TokenResponse(token,expiresAt));
    }
}

