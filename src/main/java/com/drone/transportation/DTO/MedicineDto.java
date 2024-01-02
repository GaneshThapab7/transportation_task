package com.drone.transportation.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicineDto {
 @NotBlank(message = "Invalid Name: Empty Name")
 @NotNull(message = "Invalid Name: Name is NULL")
 @Pattern(regexp = "^[a-zA-Z0-9_-]*$" ,message = "Invalid Name Format")
 private String name ;
 @NotBlank(message = "Invalid Weight: Empty Weight")
 @NotNull(message = "Invalid Weight: Weight is NULL")
 private Float weight;
 @NotBlank(message = "Invalid Code: Empty Code")
 @NotNull(message = "Invalid Coder: Code is NULL")
 @Pattern(regexp = "^[A-Z0-9_]*$",message = "Invalid Code please Fallow pattern with 'upper-case letters, underscore and numbers'")
 private String code;


}
