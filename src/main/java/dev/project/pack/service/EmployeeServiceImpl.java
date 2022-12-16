package dev.project.pack.service;

import dev.project.pack.exception.ResourceNotFoundException;
import dev.project.pack.model.Employee;
import dev.project.pack.repository.EmployeeRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> addEmployee(@NotNull Employee name) {
        Optional<Employee> employeeEmail = repository.findEmployeeByEmail(name.getEmail());
        if(employeeEmail.isPresent()){
            throw new IllegalStateException(
                    "Duplicate Email : ["+name.getEmail()+"] has exist!"
            );
        }
        return List.of(repository.save(name));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(long id) throws ResourceNotFoundException {
        boolean exist = repository.existsById(id);
        if(!exist){
            throw new ResourceNotFoundException(
                    "Employee with id ["+id+"] doesn't exist!"
            );
        }
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void updateEmployee(long id, String firstName, String lastName, String email, Integer age) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new ResourceAccessException(
                        "Employee with id ["+id+"] doesn't exist!"
                ));
        if (firstName != null && firstName.length() > 0 && !Objects.equals(employee.getFirstName(), firstName)) {
            employee.setFirstName(firstName);
        }
        if (lastName != null && lastName.length() > 0 && !Objects.equals(employee.getLastName(), lastName)) {
            employee.setLastName(lastName);
        }
        if (email != null && email.length() > 0 && !Objects.equals(employee.getEmail(), email)) {
            Optional<Employee> studentByEmail = repository.findEmployeeByEmail(email);
            if (studentByEmail.isPresent()) {
                throw new IllegalStateException("Email has exists");
            }
            employee.setEmail(email);
        }
    }

    @Override
    public void deleteEmployee(long id) throws ResourceNotFoundException {
        Optional<Employee> idEmployee = repository.findById(id);
        if (idEmployee.isEmpty()) {
            throw new ResourceNotFoundException("Employee with id ["+id+"] doesn't exist!");
        }
        repository.deleteById(id);
    }
}
