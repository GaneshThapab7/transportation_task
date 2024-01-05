package com.drone.transportation.DTO;

import com.drone.transportation.Entity.MedicineEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicineLister {
    private Float totalWeight;
    private MedicineEntity medicineEntity;
}
