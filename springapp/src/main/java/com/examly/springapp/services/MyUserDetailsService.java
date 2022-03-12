package com.examly.springapp.services;

import com.examly.springapp.MyUserDetails;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired public UserRepository userRepository;

  @Override
  public MyUserDetails loadUserByUsername(String email)
      throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByEmail(email);

    user.orElseThrow(() -> new UsernameNotFoundException("Not Found"));

    return user.map(MyUserDetails::new).get();
  }
}
