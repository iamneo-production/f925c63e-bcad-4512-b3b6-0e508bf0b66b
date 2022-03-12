package com.examly.springapp.services;

import com.examly.springapp.model.Route;
import com.examly.springapp.repository.RouteRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouteService {
  @Autowired private RouteRepository routeRepository;

  public List<Route> getRoute() {
    Iterable<Route> routes = routeRepository.findAll();
    List<Route> routeArr = new ArrayList<>();
    routes.forEach(routeArr::add);
    return routeArr;
  }

  public Route getRouteById(int id) {
    if (!routeRepository.existsById(id))
      throw new RuntimeException("Route does not exist");
    return routeRepository.findById(id).get();
  }

  public void ediRoute(Route route) {
    if (!routeRepository.existsById(route.getRouteId()))
      throw new RuntimeException("Editing unknown route is not possible");
    routeRepository.save(route);
  }

  public void saveRoute(Route route) {
    if (routeRepository.existsById(route.getRouteId()))
      throw new RuntimeException("Route already exists");
    routeRepository.save(route);
  }

  public void deleteRoute(int id) {
    if (!routeRepository.existsById(id))
      throw new RuntimeException("Deletion of unknown route is not possible");
    routeRepository.deleteById(id);
  }
}
