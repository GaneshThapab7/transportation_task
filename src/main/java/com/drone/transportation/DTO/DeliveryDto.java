package com.drone.transportation.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DeliveryDto {

    @NotBlank(message = "Invalid DroneSerialNumber: Empty DroneSerialNumber")
    @NotNull(message = "Invalid DroneSerialNumber: DroneSerialNumber is NULL")
    private String drone_serial_number;
    private List<MedicineOrderDto> medicine;
    @NotBlank(message = "Invalid Location: Empty Location")
    @NotNull(message = "Invalid Location: Location is NULL")
    private String location;
    @NotBlank(message = "Invalid Distance: Empty Distance")
    @NotNull(message = "Invalid Distance: Distance is NULL")
    private Float distance;


}
