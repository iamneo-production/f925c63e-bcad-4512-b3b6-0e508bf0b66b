package com.examly.springapp.services;

import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  @Autowired private EmployeeRepository employeeRepository;

  public List<Employee> getEmployee() {
    Iterable<Employee> employees = employeeRepository.findAll();

    List<Employee> listEmployee = new ArrayList<Employee>();

    employees.forEach(listEmployee::add);

    return listEmployee;
  }

  public Employee getEmployeeId(int id) {
    return employeeRepository.findById(id).get();
  }

  public void editEmployee(Employee employee) throws Exception {
    if (!employeeRepository.existsById(employee.id)) {
      throw new Exception("Not Found");
    }
    employeeRepository.save(employee);
  }

  public void saveEmployee(Employee employee) throws Exception {
    if (employeeRepository.existsById(employee.id)) {
      throw new Exception("User Already Exists");
    }
    employeeRepository.save(employee);
  }

  public void deleteEmployee(int id) throws Exception {
    if (!employeeRepository.existsById(id))
      throw new Exception("Employee doesn't exits");
    employeeRepository.deleteById(id);
  }
}
