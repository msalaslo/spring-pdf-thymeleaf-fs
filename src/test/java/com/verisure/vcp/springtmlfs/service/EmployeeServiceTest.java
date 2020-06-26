package com.verisure.vcp.springtmlfs.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.verisure.vcp.springtmlfs.domain.entity.Employee;
import com.verisure.vcp.springtmlfs.domain.repository.EmployeeRepository;
import com.verisure.vcp.springtmlfs.service.impl.EmployeeServiceImpl;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @Mock
    private EmployeeRepository employeeRepository;


    @Test
    void findAll() {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(Employee.builder().firstName("FIRST_NAME").build()));
        List<Employee> items = employeeService.getEmployees();
        assertEquals(items.size(), 1);
        verify(employeeRepository, times(1)).findAll();
    }


    @ParameterizedTest(name = "Employee   description: {0}, code: {1}")
    @CsvSource({
            "Description,    code",
            "Description 2,  code 2",
            "Description 3,  code 3",
            "Description 4, code 4"
    })
    void exampleParametrized(String firstName, String lastName) {
        Employee item = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(item));
        List<Employee> items = employeeService.getEmployees();
        assertEquals(items.size(), 1);
        assertEquals(items.get(0), item,
                () -> firstName + " should equal " + item.getFirstName());

        verify(employeeRepository, times(1)).findAll();
    }

}
