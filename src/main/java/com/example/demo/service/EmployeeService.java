package com.example.demo.service;

import com.example.demo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeService() {

        employees.add(
            new Employee(
                1L,
                "John",
                "john@example.com",
                "IT"
            )
        );

        employees.add(
            new Employee(
                2L,
                "Alice",
                "alice@example.com",
                "HR"
            )
        );
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employees.stream()
                .filter(employee -> employee.getId().equals(id))
                .findFirst();
    }

    public Employee createEmployee(Employee employee) {

        long newId = employees.stream()
                .mapToLong(Employee::getId)
                .max()
                .orElse(0) + 1;

        employee.setId(newId);

        employees.add(employee);

        return employee;
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {

        Optional<Employee> employeeOptional = getEmployeeById(id);

        if (employeeOptional.isPresent()) {

            Employee employee = employeeOptional.get();

            employee.setName(updatedEmployee.getName());
            employee.setEmail(updatedEmployee.getEmail());
            employee.setDepartment(updatedEmployee.getDepartment());

            return employee;
        }

        return null;
    }

    public boolean deleteEmployee(Long id) {

        return employees.removeIf(
                employee -> employee.getId().equals(id)
        );
    }
}