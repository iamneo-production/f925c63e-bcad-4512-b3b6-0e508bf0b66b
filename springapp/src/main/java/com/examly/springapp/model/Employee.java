package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) public int id;
  @Column(nullable = false) public String username;
  @Column(nullable = false) public String email;
  @Column(nullable = false) public String mobileNumber;
  @Column(nullable = false) public String vehicleModel;
  @Column(nullable = false) public String vehicleNumber;
  @Column(nullable = false) public boolean verified = true;
  @Column(nullable = false) public boolean active = true;

  public int getId() { return this.id; }

  public void setId(int id) { this.id = id; }

  public String getUsername() { return this.username; }

  public void setUsername(String username) { this.username = username; }

  public String getEmail() { return this.email; }

  public void setEmail(String email) { this.email = email; }

  public String getMobileNumber() { return this.mobileNumber; }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getVehicleModel() { return this.vehicleModel; }

  public void setVehicleModel(String vehicleModel) {
    this.vehicleModel = vehicleModel;
  }

  public String getVehicleNumber() { return this.vehicleNumber; }

  public void setVehicleNumber(String vehicleNumber) {
    this.vehicleNumber = vehicleNumber;
  }

  public boolean isVerified() { return this.verified; }

  public void setVerified(boolean verified) { this.verified = verified; }

  public boolean isActive() { return this.active; }

  public void setActive(boolean active) { this.active = active; }
}
