package com.drone.transportation.DTO;

import com.drone.transportation.Entity.MedicineEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MedicineCollection {
    List<MedicineEntity> medicineEntities;
    Float totalWeight;
}
