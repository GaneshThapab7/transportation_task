package com.drone.transportation.Entity;


import com.drone.transportation.enms.DroneStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;


@Entity
@Table(name = "drone_log")

@Data
@Builder
public class DroneLogEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneStatus;
    @Column(name = "battery_capacity")
    private Float battery_capacity;

    public DroneLogEntity(Long id, DroneStatus status, Float weight) {
        this.id = id;
        this.droneStatus = status;
        this.battery_capacity = weight;
    }


    public DroneLogEntity() {

    }
}
