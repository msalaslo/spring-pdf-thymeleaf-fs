package com.verisure.vcp.springtmlfs.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.verisure.vcp.springtmlfs.api.converter.EmployeeConverter;
import com.verisure.vcp.springtmlfs.api.dto.EmployeeDTO;
import com.verisure.vcp.springtmlfs.domain.entity.Employee;
import com.verisure.vcp.springtmlfs.service.EmployeePDFGenerationService;
import com.verisure.vcp.springtmlfs.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;



/**
 * Employee controller.
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 */
@Slf4j
@RestController
@RequestMapping("/employees")
@Tag(name = "Employee controller")
public class EmployeeController {

    @Autowired
    private EmployeeConverter employeeConverter;

    @Autowired
    private EmployeePDFGenerationService employeePDFGenerationService;
    
    @Autowired
    private EmployeeService employeeService;

    
    @PostMapping(produces = "application/pdf")
    @Operation(
            description = "Generate a PDF with the one employer"
    )
    public @ResponseBody byte[] generatePDF(@Valid @RequestBody EmployeeDTO employeeDTO) {
    	//@PathVariable(value = "templateName") String templateName,@PathVariable(value = "templateName") String templateName,
        LOGGER.debug("Generate PDF:: Employees received" + employeeDTO);

        Employee employee = employeeConverter.toEmployee(employeeDTO);
        return employeePDFGenerationService.generatePDF("employee-template", employee);
    }
    
    @GetMapping(produces = "application/json")
    @ResponseBody
    @Operation(
            description = "view the list of ALL application items",
            responses = {
                    @ApiResponse(content = @Content(array = @ArraySchema(schema = @Schema(implementation = EmployeeDTO.class))), responseCode = "200")
            }
    )
    public List<EmployeeDTO> getEmployees() {
        LOGGER.debug("getItems::Trying to retrieve all items");
        return employeeService.getEmployees().stream()
                .map(employeeConverter::toEmployeeDto)
                .collect(Collectors.toList());
    }


}
