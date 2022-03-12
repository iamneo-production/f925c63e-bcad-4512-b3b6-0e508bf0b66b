package com.examly.springapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Route {
  @Id @GeneratedValue(strategy = GenerationType.AUTO) public int routeId;
  @Column(nullable = false) public String startPoint;
  @Column(nullable = false) public String endPoint;
  @Column(nullable = false) public int distance;

  public int getRouteId() { return this.routeId; }

  public void setRouteId(int routeId) { this.routeId = routeId; }

  public String getStartPoint() { return this.startPoint; }

  public void setStartPoint(String startPoint) { this.startPoint = startPoint; }

  public String getEndPoint() { return this.endPoint; }

  public void setEndPoint(String endPoint) { this.endPoint = endPoint; }

  public int getDistance() { return this.distance; }

  public void setDistance(int distance) { this.distance = distance; }
}
