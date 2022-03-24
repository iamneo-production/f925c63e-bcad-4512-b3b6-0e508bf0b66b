package com.examly.springapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class User {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  public String id;
  @Column(nullable = false) public String name;
  @Email @Column(nullable = false, unique = true) public String email;
  @Pattern(regexp = "(^$|[0-9]{10})")
  @Column(nullable = false)
  public String mobileNumber;
  @Column(nullable = false) public boolean status = true;
  @JsonProperty(access = Access.WRITE_ONLY)
  @Column(nullable = false)
  public String password;
  @Column(nullable = false) public String role;

  public String getRole() { return this.role; }

  public void setRole(String role) { this.role = role; }

  public String getPassword() { return this.password; }

  public void setPassword(String password) { this.password = password; }

  public String getId() { return this.id; }

  public void setId(String id) { this.id = id; }

  public String getName() { return this.name; }

  public void setName(String name) { this.name = name; }

  public String getEmail() { return this.email; }

  public void setEmail(String email) { this.email = email; }

  public String getMobileNumber() { return this.mobileNumber; }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public boolean isStatus() { return this.status; }

  public void setStatus(boolean status) { this.status = status; }
}
