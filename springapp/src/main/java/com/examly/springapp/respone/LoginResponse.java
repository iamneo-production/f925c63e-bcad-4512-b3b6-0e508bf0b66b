package com.examly.springapp.respone;

public class LoginResponse {
  public String jwt;
  public String role;
  public LoginResponse(String jwt, String role) {
    this.jwt = jwt;
    this.role = role;
  }
}
