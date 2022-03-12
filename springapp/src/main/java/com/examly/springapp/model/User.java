package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  public String id;
  @Column(nullable = false) public String name;
  @Column(nullable = false, unique = true) public String email;
  @Column(nullable = false) public Long mobileNumber;
  @Column(nullable = false) public boolean status = true;
  @Column(nullable = false) public String password;
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

  public Long getMobileNumber() { return this.mobileNumber; }

  public void setMobileNumber(Long mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public boolean isStatus() { return this.status; }

  public void setStatus(boolean status) { this.status = status; }
}
