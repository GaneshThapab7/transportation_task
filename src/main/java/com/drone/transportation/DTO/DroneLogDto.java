package com.drone.transportation.DTO;

import com.drone.transportation.enms.DroneStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneLogDto {
    @NotBlank(message = "Invalid Serial Number: Empty Serial Number")
    @NotNull(message = "Invalid Serial Number: Serial Number is NULL")
    @Size(min = 10,max = 100,message = "Invalid Serial Number Length should be 10-100")
    private String serialNumber;
    private String droneStatus;
    @NotBlank(message = "Invalid Battery Capacity: Empty Battery Capacity")
    @NotNull(message = "Invalid Battery Capacity: Battery Capacity is NULL")
    @Size(min = 0,max = 100,message = "Invalid Battery Capacity Percentage should be 0-100%")
    private Float battery_capacity;

}
