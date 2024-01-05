package com.drone.transportation.Util;

import com.drone.transportation.DTO.DroneDto;
import com.drone.transportation.DTO.MedicineDto;
import com.drone.transportation.Entity.DroneEntity;
import com.drone.transportation.Entity.MedicineEntity;
import com.drone.transportation.enms.DroneModel;
import com.drone.transportation.enms.DroneStatus;

public class Mapper {




    public DroneEntity mepDroneEntity(DroneDto droneDto){
        return DroneEntity.builder()
                .battery_capacity(100F)
                .droneStatus(DroneStatus.StandBy)
                .weightLimit(droneDto.getWeightLimit())
                .operationalSpeed(droneDto.getOperationalSpeed())
                .model(DroneModel.valueOf(droneDto.getModel()))
                .build();
    }
    public MedicineEntity mapMedicineEntity(MedicineDto medicineDto,String filePath){
        return MedicineEntity.builder()
                .name(medicineDto.getName())
                .code(medicineDto.getCode())
                .weight(medicineDto.getWeight())
                .image(filePath)
                .build();
    }




}
