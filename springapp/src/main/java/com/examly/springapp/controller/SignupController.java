package com.examly.springapp.controller;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.respone.MessageResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {
  @Autowired private PasswordEncoder passwrodEncoder;
  @Autowired private UserRepository userRepository;
  @PostMapping("/signup")
  public ResponseEntity<?> saveCustomer(@Valid @RequestBody User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new IllegalArgumentException("User Already Exits");
    }
    user.setRole("ROLE_USER");
    user.setPassword(passwrodEncoder.encode(user.getPassword()));
    userRepository.save(user);

    return new ResponseEntity<>(
        new MessageResponse("Account Created Successfully"),
        HttpStatus.CREATED);
  }
}
