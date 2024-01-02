package com.drone.transportation.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "medicine")
@Data
public class MedicineEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "weight;")
    private Float weight;
    @Column(name = "code")
    private String code;
    @Column(name = "image")
    private String image;

}
