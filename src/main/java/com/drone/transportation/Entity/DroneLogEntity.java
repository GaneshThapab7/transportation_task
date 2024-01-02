package com.drone.transportation.Entity;


import com.drone.transportation.enms.DroneStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "drone_log")
@Data
public class DroneLogEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneStatus;
    @Column(name = "battery_capacity")
    private Float battery_capacity;

}
