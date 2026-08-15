package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    private final EmployeeRepository employeeRepository =
            Mockito.mock(EmployeeRepository.class);

    private final EmployeeService employeeService =
            new EmployeeService(employeeRepository);


    @Test
    void shouldReturnAllEmployees() {

        Employee employee1 =
                new Employee("Sukanya",
                        "sukanya@example.com",
                        "DevOps");

        Employee employee2 =
                new Employee("Ravi",
                        "ravi@example.com",
                        "Cloud");

        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee1, employee2));

        List<Employee> employees =
                employeeService.getAllEmployees();

        assertEquals(2, employees.size());

        verify(employeeRepository).findAll();
    }


    @Test
    void shouldReturnEmployeeById() {

        Employee employee =
                new Employee("Sukanya",
                        "sukanya@example.com",
                        "DevOps");

        employee.setId(1L);

        when(employeeRepository.findById(1L))
                .thenReturn(Optional.of(employee));

        Optional<Employee> result =
                employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());

        assertEquals("Sukanya",
                result.get().getName());
    }


    @Test
    void shouldCreateEmployee() {

        Employee employee =
                new Employee("Sukanya",
                        "sukanya@example.com",
                        "DevOps");

        when(employeeRepository.save(employee))
                .thenReturn(employee);

        Employee result =
                employeeService.createEmployee(employee);

        assertEquals("Sukanya",
                result.getName());

        verify(employeeRepository).save(employee);
    }


    @Test
    void shouldDeleteEmployee() {

        when(employeeRepository.existsById(1L))
                .thenReturn(true);

        boolean result =
                employeeService.deleteEmployee(1L);

        assertTrue(result);

        verify(employeeRepository)
                .deleteById(1L);
    }
}