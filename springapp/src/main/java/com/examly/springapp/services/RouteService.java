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
    List<Route> routeArr = new ArrayList<Route>();
    routes.forEach(routeArr::add);
    return routeArr;
  }

  public Route getRouteById(int id) throws Exception {
    if (!routeRepository.existsById(id))
      throw new Exception("Route does not exists");
    Route route = routeRepository.findById(id).get();
    return route;
  }

  public void ediRoute(Route route) throws Exception {
    if (!routeRepository.existsById(route.getRouteId()))
      throw new Exception("Route does not exists");
    routeRepository.save(route);
  }

  public void saveRoute(Route route) throws Exception {
    if (routeRepository.existsById(route.getRouteId()))
      throw new Exception("Route already exists");
    routeRepository.save(route);
  }

  public void deleteRoute(int id) throws Exception {
    if (!routeRepository.existsById(id))
      throw new Exception("Route does not exists");
    routeRepository.deleteById(id);
  }
}
