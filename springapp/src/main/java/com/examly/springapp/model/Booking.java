package com.examly.springapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Booking {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) public int bookingId;

  @ManyToOne(optional = false) public Route route;

  @OneToOne(optional = false) public User user;

  public int getBookingId() { return this.bookingId; }

  public void setBookingId(int bookingId) { this.bookingId = bookingId; }

  public Route getRoute() { return this.route; }

  public void setRoute(Route route) { this.route = route; }

  public User getUser() { return this.user; }

  public void setUser(User user) { this.user = user; }
}
