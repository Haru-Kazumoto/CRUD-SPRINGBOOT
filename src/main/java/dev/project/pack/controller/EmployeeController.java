package dev.project.pack.controller;

import dev.project.pack.exception.ResourceNotFoundException;
import dev.project.pack.model.Employee;
import dev.project.pack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    //TODO : GetEmployee (SUCCESS)
    @GetMapping("/getAll")
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }

    //TODO : Get Employee by id (SUCCESS)
    @GetMapping("/employee/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException {
        return service.getEmployeeById(employeeId);
    }

    //TODO : Register Employee (SUCCESS)
    @PostMapping("/save")
    public List<Employee> saveEmployee(@RequestBody Employee employee){
        return service.addEmployee(employee);
    }

    //TODO : Update Employee
    @PutMapping("/updateEmployee/{id}")
    public void updateEmployee(@PathVariable(value = "id") long id,
                               @RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName,
                               @RequestParam(required = false) String email,
                               @RequestParam(required = false) Integer age){
        service.updateEmployee(id, firstName, lastName, email, age);
    }

    //TODO : Delete Employee (SUCCESS)
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable(value = "id") long id) throws ResourceNotFoundException {
        service.deleteEmployee(id);
    }
}
