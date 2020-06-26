package com.verisure.vcp.springtmlfs.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Employee DTO object. 
 *
 * @since 1.0.0
 * @author FaaS [faas@securitasdirect.es]
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO extends BaseDTO {

    @Schema(description = "Employee name", required = true)
    private String firstName;
    
    @Schema(description = "Employee last name", required = true)
    private String lastName;
    
    @Schema(description = "Employee street", required = false)
    private String street;
    
    @Schema(description = "Employee zip code", required = false)
    private String zipCode;
    
    @Schema(description = "Employee city", required = false)
    private String city;


}
