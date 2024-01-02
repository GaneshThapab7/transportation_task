package com.drone.transportation.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MedicineOrderDto {

 @NotBlank(message = "Invalid  Medicine Code: Empty Medicine Code")
 @NotNull(message = "Invalid  Medicine Coder: Medicine Code is NULL")
 private String code;
 @NotBlank(message = "Invalid Medicine quantity: Empty Medicine quantity")
 @NotNull(message = "Invalid Medicine quantity: Medicine  quantity is NULL")
 private int quantity;

}
