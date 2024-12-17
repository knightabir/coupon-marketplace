package com.marketplace.couponMarketplace.controller.auth;
import com.marketplace.couponMarketplace.model.User;
import com.marketplace.couponMarketplace.service.UserService;
import com.marketplace.couponMarketplace.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @GetMapping("/healthCheck")
    public HashMap<String, String> healthCheck() {
        HashMap<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Server is running");
        response.put("code", "200");
        log.info("Response: {}", response);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();

        if (user == null || user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            response.put("status", "error");
            response.put("message", "Invalid email");
            response.put("code", "400");
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } else if (user.getPassword() == null || user.getPassword().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Invalid password");
            response.put("code", "400");
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());
        if (existingUser.isEmpty()) {
            response.put("status", "error");
            response.put("message", "User not found");
            response.put("code", "404");
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        var email = user.getEmail();
        var password = user.getPassword();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            System.out.println(userDetails);
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            response.put("status", "success");
            response.put("message", "User logged in successfully");
            response.put("token", jwt);
            response.put("code", "200");
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            response.put("status", "error");
            response.put("message", "Invalid username or password");
            response.put("code", "401");
            log.info("Response: {}", response);
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            response.put("code", "400");
            log.info("Response: {}", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody(required = false) User user) {
        Map<String, String> response = new HashMap<>();

        if (user == null) {
            response.put("status", "error");
            response.put("message", "Request body is missing");
            response.put("code", "400");
            log.info("Response: {}", response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Email is required");
            response.put("code", "400");
            log.info("Response: {}", response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Password is required");
            response.put("code", "400");
            log.info("Response: {}", response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            response.put("status", "error");
            response.put("message", "First name is required");
            response.put("code", "400");
            log.info("Response: {}", response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Last name is required");
            response.put("code", "400");
            log.info("Response: {}", response);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<User> existingUser = userService.getUserByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            response.put("status", "error");
            response.put("message", "User with this email already exists");
            response.put("code", "409");
            log.info("Response: {}", response);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        user.setActive(true);
        user.setRoles(List.of("ROLE_USER"));
        User savedUser = userService.registerUser(user);
        if (savedUser != null) {
            response.put("status", "success");
            response.put("message", "User registered successfully");
            response.put("code", "200");
        } else {
            response.put("status", "error");
            response.put("message", "Failed to register user");
            response.put("code", "500");
        }

        log.info("Response: {}", response);
        return ResponseEntity.ok(response);
    }
}