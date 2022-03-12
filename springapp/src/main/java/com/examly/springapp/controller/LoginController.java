package com.examly.springapp.controller;

import com.examly.springapp.MyUserDetails;
import com.examly.springapp.request.LoginRequest;
import com.examly.springapp.respone.LoginResponse;
import com.examly.springapp.services.MyUserDetailsService;
import com.examly.springapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired private MyUserDetailsService myUserDetailsService;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtUtil jwtUtil;

  @PostMapping("/login")
  public ResponseEntity<?> checkUser(@RequestBody LoginRequest loginRequest) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                                                  loginRequest.getPassword()));

      MyUserDetails myUserDetails =
          myUserDetailsService.loadUserByUsername(loginRequest.getEmail());

      String jwt = jwtUtil.generateToken(myUserDetails);

      return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);

    } catch (BadCredentialsException e) {
      return new ResponseEntity<>("Authentication Faled",
                                  HttpStatus.BAD_REQUEST);
    }
  }
}
