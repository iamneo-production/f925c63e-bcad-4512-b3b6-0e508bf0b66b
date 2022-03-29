package com.examly.springapp.controller;

import com.examly.springapp.model.Employee;
import com.examly.springapp.model.Route;
import com.examly.springapp.respone.DataResponse;
import com.examly.springapp.respone.MessageResponse;
import com.examly.springapp.services.EmployeeService;
import com.examly.springapp.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

  @Autowired private RouteService routeService;
  @Autowired private EmployeeService employeeService;
  @GetMapping({"/admin/routes", "/routes"})
  public ResponseEntity<?> getRoute() {
    return new ResponseEntity<>(new DataResponse(routeService.getRoute()),
                                HttpStatus.OK);
  }

  @GetMapping({"/route/{id}"})
  public ResponseEntity<?> getRouteById(@PathVariable int id) {
    try {
      Route r = routeService.getRouteById(id);
      return new ResponseEntity<>(r, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("admin/deleteRoutes/{id}")
  public ResponseEntity<?> deleteRoute(@PathVariable int id) {
    try {
      routeService.deleteRoute(id);
      return new ResponseEntity<>(
          new MessageResponse("Route Deleted Successfully"),
          HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping("/admin/editRoutes")
  public ResponseEntity<?> ediRoute(@RequestBody Route route) {
    try {
      routeService.editRoute(route);
      Employee emp = employeeService.getEmployeeId(route.employee.getId());
      route.employee = emp;
      return new ResponseEntity<>(route, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/admin/addRoutes")
  public ResponseEntity<?> saveRoute(@RequestBody Route route) {
    try {
      routeService.saveRoute(route);
      Employee emp = employeeService.getEmployeeId(route.employee.getId());
      route.employee = emp;
      return new ResponseEntity<>(route, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
}
