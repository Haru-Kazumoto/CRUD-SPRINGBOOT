package dev.project.pack.service;

import dev.project.pack.exception.ResourceNotFoundException;
import dev.project.pack.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> addEmployee(Employee name);

    List<Employee> getAllEmployee();

    Optional<Employee> getEmployeeById(long id) throws ResourceNotFoundException;

    void updateEmployee(long id, String firstName, String lastName, String email, Integer age);

    void deleteEmployee(long id) throws ResourceNotFoundException;

}
