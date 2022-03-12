package com.examly.springapp;

import com.examly.springapp.model.User;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

  public String name;
  public String passwrod;
  public String email;
  public boolean status;

  public List<GrantedAuthority> role;

  public MyUserDetails(User user) {
    this.name = user.getName();
    this.passwrod = user.getPassword();
    this.email = user.getEmail();
    this.status = user.isStatus();
    this.role = Arrays.stream(user.getRole().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
  }

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  public String getPasswrod() { return this.passwrod; }

  public void setPasswrod(String passwrod) { this.passwrod = passwrod; }

  public String getEmail() { return this.email; }

  public void setEmail(String email) { this.email = email; }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.role;
  }

  @Override
  public String getPassword() {
    return this.passwrod;
  }

  @Override
  public String getUsername() {
    return this.name;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return this.status;
  }
}
