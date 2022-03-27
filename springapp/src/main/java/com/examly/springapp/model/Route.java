package com.examly.springapp.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Route {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) public int routeid;
  @Column(nullable = false) public String startPoint;
  @Column(nullable = false) public String endPoint;
  @Column(nullable = false) public int distance;
  @Column(nullable = false) public int seats = 4;
  @ManyToOne(optional = false) public Employee employee;

  @OneToMany(mappedBy = "route", cascade = CascadeType.REMOVE)
  private Set<Booking> booking;

  public Employee getEmployee() { return this.employee; }

  public void setEmployee(Employee employee) { this.employee = employee; }

  public int getRouteId() { return this.routeid; }

  public void setRouteId(int routeId) { this.routeid = routeId; }

  public String getStartPoint() { return this.startPoint; }

  public void setStartPoint(String startPoint) { this.startPoint = startPoint; }

  public String getEndPoint() { return this.endPoint; }

  public void setEndPoint(String endPoint) { this.endPoint = endPoint; }

  public int getDistance() { return this.distance; }

  public void setDistance(int distance) { this.distance = distance; }

  public int getSeats() { return this.seats; }

  public void setSeats(int seats) { this.seats = seats; }
}
