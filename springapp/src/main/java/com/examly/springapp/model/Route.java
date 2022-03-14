package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Route {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) public int routeId;
  @Column(nullable = false) public String startPoint;
  @Column(nullable = false) public String endPoint;
  @Column(nullable = false) public int distance;
  @Column(nullable = false) public int seats = 4;
  @ManyToOne(optional = false) public Employee employee;

  public Employee getEmployee() { return this.employee; }

  public void setEmployee(Employee employee) { this.employee = employee; }

  public int getRouteId() { return this.routeId; }

  public void setRouteId(int routeId) { this.routeId = routeId; }

  public String getStartPoint() { return this.startPoint; }

  public void setStartPoint(String startPoint) { this.startPoint = startPoint; }

  public String getEndPoint() { return this.endPoint; }

  public void setEndPoint(String endPoint) { this.endPoint = endPoint; }

  public int getDistance() { return this.distance; }

  public void setDistance(int distance) { this.distance = distance; }

  public int getSeats() { return this.seats; }

  public void setSeats(int seats) { this.seats = seats; }
}
