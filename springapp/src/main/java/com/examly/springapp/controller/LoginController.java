package com.examly.springapp.controller;

import com.examly.springapp.MyUserDetails;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.request.LoginRequest;
import com.examly.springapp.respone.LoginResponse;
import com.examly.springapp.services.MyUserDetailsService;
import com.examly.springapp.utils.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  @Autowired private MyUserDetailsService myUserDetailsService;

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private JwtUtil jwtUtil;

  @Autowired private UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<?>
  checkUser(@Valid @RequestBody LoginRequest loginRequest) {
    try {

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                                                  loginRequest.getPassword()));

      MyUserDetails myUserDetails =
          myUserDetailsService.loadUserByUsername(loginRequest.getEmail());

      String jwt = jwtUtil.generateToken(myUserDetails);
      return new ResponseEntity<>(
          new LoginResponse(
              jwt, myUserDetails.getAuthorities().toArray()[0].toString()),
          HttpStatus.OK);

    } catch (BadCredentialsException e) {
      return new ResponseEntity<>("Authentication Faled",
                                  HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/editCustomer")
  public ResponseEntity<?>
  editCustomer(@Valid HttpServletRequest httpServletRequest,
               @RequestBody User bodyUser) {
    try {
      String email = (String)httpServletRequest.getAttribute("email");
      User user = userRepository.findByEmail(email).get();

      bodyUser.setId(user.getId());
      bodyUser.setPassword(user.getPassword());
      bodyUser.setRole(user.getRole());

      userRepository.save(bodyUser);

      return new ResponseEntity<>("Updated Successfully", HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
