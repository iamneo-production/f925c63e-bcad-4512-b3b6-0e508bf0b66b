package com.examly.springapp.services;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepository;
import com.examly.springapp.repository.RouteRepository;
import com.examly.springapp.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
  @Autowired private EmployeeRepository employeeRepository;
  @Autowired private RouteRepository routeRepository;

  public List<Employee> getEmployee() {
    Iterable<Employee> employees = employeeRepository.findAll();

    List<Employee> listEmployee = new ArrayList<>();

    employees.forEach(listEmployee::add);

    return listEmployee;
  }

  public Employee getEmployeeId(int id) {
    return employeeRepository.findById(id).get();
  }

  public void editEmployee(Employee employee) {
    if (!employeeRepository.existsById(employee.id)) {
      throw new IllegalArgumentException("Not Found");
    }
    employeeRepository.save(employee);
  }

  public void saveEmployee(Employee employee) {
    if (employeeRepository.existsById(employee.id)) {
      throw new IllegalArgumentException("User Already Exits");
    }
    employeeRepository.save(employee);
  }

  public void deleteEmployee(int id) {
    if (!employeeRepository.existsById(id))
      throw new IllegalArgumentException("User Already Exits");

    if (routeRepository.existsByEmployeeId(id))
      throw new IllegalArgumentException(
          "Remove the Employee from route first, then delete the account");
    employeeRepository.deleteById(id);
  }
}
