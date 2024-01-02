package com.drone.transportation.Entity;

import com.drone.transportation.enms.DroneModel;
import com.drone.transportation.enms.DroneStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drone")
@Data
public class DroneEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "serial_number", length = 100)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column(name = "weight_limit")
    private Integer weightLimit;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneStatus;
    @Column(name = "battery_capacity")
    private Float battery_capacity;
    @Column(name = "operational_speed")
    private Float operationalSpeed;




}
