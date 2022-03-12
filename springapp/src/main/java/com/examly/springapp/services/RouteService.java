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

  public Route getRouteById(int id) throws Throwable {
    if (!routeRepository.existsById(id))
      throw new Throwable("Route does not exist");
    return routeRepository.findById(id).get();
  }

  public void ediRoute(Route route) throws Throwable {
    if (!routeRepository.existsById(route.getRouteId()))
      throw new Throwable("Editing unknown route is not possible");
    routeRepository.save(route);
  }

  public void saveRoute(Route route) throws Throwable {
    if (routeRepository.existsById(route.getRouteId()))
      throw new Throwable("Route already exists");
    routeRepository.save(route);
  }

  public void deleteRoute(int id) throws Throwable {
    if (!routeRepository.existsById(id))
      throw new Throwable("Deletion of unknown route is not possible");
    routeRepository.deleteById(id);
  }
}
