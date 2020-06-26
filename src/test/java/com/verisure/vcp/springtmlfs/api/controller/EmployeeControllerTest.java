package com.verisure.vcp.springtmlfs.api.controller;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.verisure.vcp.springtmlfs.api.converter.EmployeeConverter;
import com.verisure.vcp.springtmlfs.domain.entity.Employee;
import com.verisure.vcp.springtmlfs.service.EmployeeService;

@SpringBootTest
public class EmployeeControllerTest {

    @Mock
    EmployeeService employeeService;

    @Mock
    EmployeeConverter employeeConverter;

    @InjectMocks
    EmployeeController employeeController;

    MockMvc mockMvc;

    Employee employee;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
        Employee.builder()
                .firstName("FIRST_NAME")
                .lastName("LAST_NAME")
                .build();
    }

    @Test
    void getAllItemsOK() throws Exception {
        given(employeeService.getEmployees()).willReturn(Arrays.asList(employee));
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(1)));
    }

}
