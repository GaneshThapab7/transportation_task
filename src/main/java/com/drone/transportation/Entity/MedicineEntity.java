package com.drone.transportation.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "medicine")
@Data
@AllArgsConstructor
@Builder
public class MedicineEntity extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "name",unique = true)
    private String name;
    @Column(name = "weight;")
    private Float weight;
    @Column(name = "code",unique = true)
    private String code;
    @Column(name = "image")
    private String image;

    public MedicineEntity() {

    }
}
