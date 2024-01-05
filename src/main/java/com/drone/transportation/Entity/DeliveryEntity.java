package com.drone.transportation.Entity;


import com.drone.transportation.enms.TransportStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "delivery")
@Data
@Builder
public class DeliveryEntity extends  BaseEntity{
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "delivery_iD")
    private String deliveryID;
    @Enumerated(EnumType.STRING)
    private TransportStatus transportStatus;
    @Column(name = "estimated_uration")
    private String estimatedDuration;
    @Column(name = "cargo_weight")
    private Float cargoWeight;
    @Column(name = "start_time")
    private LocalDateTime startDateTine;
    @Column(name = "location")
    private String location;
    @Column(name = "distance")
    private Float distance;
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", referencedColumnName = "serial_number")
    private DroneEntity drone;
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "delivery_medicine",
            joinColumns = @JoinColumn(name = "delivery_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    private List<MedicineEntity> medicineList;

    public DeliveryEntity() {

    }


    public void getEstimateTime(){
        float time=this.distance/this.drone.getOperationalSpeed();
        int hours = (int) time;
        int minutes = (int) (60 * (time - hours));
        this.estimatedDuration=  String.valueOf(hours)+":"+String.valueOf(minutes);
        LocalDateTime endTime=startDateTine;
        if(hours>0){
            endTime=  endTime.plusHours(hours);
        }
        if(minutes<0){
            endTime=endTime.plusMinutes(minutes);
        }
        this.endTime=endTime;

    }

}
