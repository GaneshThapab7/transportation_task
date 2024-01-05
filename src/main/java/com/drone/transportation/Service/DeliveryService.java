package com.drone.transportation.Service;

import com.drone.transportation.DTO.DeliveryDto;
import com.drone.transportation.DTO.MedicineCollection;
import com.drone.transportation.DTO.MedicineLister;
import com.drone.transportation.DTO.MedicineOrderDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Entity.DeliveryEntity;
import com.drone.transportation.Entity.DroneEntity;
import com.drone.transportation.Entity.MedicineEntity;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.DeliveryInterface;
import com.drone.transportation.Repo.DeliveryRepo;
import com.drone.transportation.Repo.DroneRepo;
import com.drone.transportation.Repo.MedicineRepo;
import com.drone.transportation.enms.DroneStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeliveryService implements DeliveryInterface {

    private final DeliveryRepo deliveryRepo;
    private final DroneRepo droneRepo;
    private final MedicineRepo medicineRepo;


    @Override
    public ResponseDto createDelivery(DeliveryDto deliveryDto, String txnId) throws GlobleException {
        DroneEntity droneEntity=droneRepo.findBySerialNumber(deliveryDto.getDrone_serial_number());
        if(droneEntity==null){
            throw  new GlobleException("Drone with serial number :"+deliveryDto.getDrone_serial_number()+" dose not exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        if(!droneEntity.getDroneStatus().equals(DroneStatus.StandBy)){
            throw  new GlobleException("Drone cannot be requested for delivery now as it is in "+ droneEntity.getDroneStatus().name()+ " Mode", HttpStatus.BAD_REQUEST,txnId);

        }
        if(droneEntity.getBattery_capacity()<=25){
            throw  new GlobleException("Drone dose not have enough charge for Delivery", HttpStatus.BAD_REQUEST,txnId);

        }
        MedicineCollection medicineCollection= medicineListAndDroneWeight(deliveryDto.getMedicine(), droneEntity.getWeightLimit(),txnId);
        DeliveryEntity deliveryEntity=DeliveryEntity.builder()
                .deliveryID(getDeleveryId())
                .cargoWeight(medicineCollection.getTotalWeight())
                .medicineList(medicineCollection.getMedicineEntities())
                .distance(deliveryDto.getDistance())
                .location(deliveryDto.getLocation())
                .startDateTine(LocalDateTime.now())
                .build();
        deliveryEntity.getEstimateTime();
        return new ResponseDto("Delivery Created",deliveryEntity,txnId);
    }

    @Override
    public ResponseDto getDeliveryByDeliveryID(String deliveryID, String txnId) throws GlobleException {
        DeliveryEntity deliveryEntity=deliveryRepo.findByDeliveryID(deliveryID);
        if(deliveryEntity==null){
            throw  new GlobleException("Delivery with DeliveryId :"+deliveryID+" dose not exist !!", HttpStatus.BAD_REQUEST,txnId);

        }
        return new ResponseDto("Delivery Fetch with DeliveryId",deliveryEntity,txnId);
    }

    @Override
    public ResponseDto getDeliveryByDroneID(String droneIdSerialNumber, String txnId) throws GlobleException {
        return new ResponseDto("Delivery List by SerialNumber",deliveryRepo.findAllByDrone_SerialNumber(droneIdSerialNumber),txnId);
    }


    public MedicineCollection medicineListAndDroneWeight(List<MedicineOrderDto> medicineOrderDtoList,Float weightLimit,String txnId) throws GlobleException {
        List<MedicineLister> medicineListers=new ArrayList<>();


        medicineOrderDtoList.forEach(e->{
            MedicineEntity medicineEntity=medicineRepo.findByCode(e.getCode());
            if(medicineEntity==null){
                throw  new RuntimeException("Medicine with code :"+e.getCode()+" already exist !!");
            }
            medicineListers.add(
                    MedicineLister.builder()
                            .medicineEntity(medicineEntity)
                            .totalWeight(e.getQuantity()*medicineEntity.getWeight())
                            .build()

            );
        });
        Float totalWeight=medicineListers.parallelStream().map(MedicineLister::getTotalWeight).reduce(0.0f,Float::sum);
        if(totalWeight>weightLimit){
            throw  new GlobleException("Max weigh limit for requested Drone is :"+ weightLimit, HttpStatus.BAD_REQUEST,txnId);

        }
        List<MedicineEntity> medicineEntities=medicineListers.parallelStream().map(MedicineLister::getMedicineEntity).collect(Collectors.toList());
        return new MedicineCollection(medicineEntities,totalWeight);

    }

    public String getDeleveryId(){
        boolean isUnique=true;
        String newDeliveryId="";
        while (isUnique){
            String deliveryId= UUID.randomUUID().toString();
            if(deliveryRepo.countByDeliveryID(deliveryId)==0){
                newDeliveryId =deliveryId;
                isUnique=false;
            }
        }
        return newDeliveryId;
    }
}
