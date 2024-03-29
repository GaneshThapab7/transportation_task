package com.drone.transportation.Entity;

import com.drone.transportation.enms.DroneModel;
import com.drone.transportation.enms.DroneStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "drone")
@Data
@AllArgsConstructor
@Builder
public class DroneEntity extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "serial_number", length = 100,unique = true)
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneModel model;
    @Column(name = "weight_limit")
    private Float weightLimit;
    @Enumerated(EnumType.STRING)
    private DroneStatus droneStatus;
    @Column(name = "battery_capacity")
    private Float battery_capacity;
    @Column(name = "operational_speed")
    private Float operationalSpeed;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "drone_log_link",
            joinColumns = @JoinColumn(name = "drone_id"),
            inverseJoinColumns = @JoinColumn(name = "drone_log_list_id"))
    private List<DroneLogEntity> drone_log_list;

    public DroneEntity() {

    }
}
