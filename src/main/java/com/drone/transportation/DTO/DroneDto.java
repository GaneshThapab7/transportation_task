package com.drone.transportation.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;



@Data
public class DroneDto {

    @NotBlank(message = "Invalid Serial Number: Empty Serial Number")
    @NotNull(message = "Invalid Serial Number: Serial Number is NULL")
    @Size(min = 10,max = 100,message = "Invalid Serial Number Length should be 10-100")
    private String serialNumber;
    @NotBlank(message = "Invalid Model: Empty Model")
    @NotNull(message = "Invalid Model: Model is NULL")
    private String model;
    @NotBlank(message = "Invalid Weight Limit: Empty Weight Limit")
    @NotNull(message = "Invalid Weight Limit: Weight Limit is NULL")
    @Size(max = 500,message = "Max weight limit is 500gr")
    private Float weightLimit;
    @NotBlank(message = "Invalid Operational Speed: Empty Operational Speed")
    @NotNull(message = "Invalid Operational Speed: Operational Speed is NULL")
    private Float operationalSpeed;
}
