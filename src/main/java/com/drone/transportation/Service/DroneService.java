package com.drone.transportation.Service;

import com.drone.transportation.DTO.DroneDto;
import com.drone.transportation.DTO.DroneLogDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Entity.DroneEntity;
import com.drone.transportation.Entity.DroneLogEntity;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.DroneInterface;
import com.drone.transportation.Repo.DroneLogRepo;
import com.drone.transportation.Repo.DroneRepo;
import com.drone.transportation.Util.Mapper;
import com.drone.transportation.enms.DroneStatus;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class DroneService implements DroneInterface {

    private final DroneRepo droneRepo;
    private final DroneLogRepo droneLogRepo;


    @Override
    public ResponseDto saveDrone(DroneDto droneDto,String txnId) throws GlobleException {
        Mapper mapper=new Mapper();
        if(droneRepo.countBySerialNumber(droneDto.getSerialNumber())!=0){
            throw  new GlobleException("Drone with serial number :"+droneDto.getSerialNumber()+" already exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        try {
            return new ResponseDto("Drone Save SuccessFull",droneRepo.save(mapper.mepDroneEntity(droneDto)),txnId);
        }catch (Exception e){
            throw new GlobleException(e.getMessage(),HttpStatus.BAD_REQUEST,txnId);
        }
    }

    @Override
    public ResponseDto getBySerialNumber(String serialNumber, String txnId) throws GlobleException {
        DroneEntity droneEntity=droneRepo.findSerialNumber(serialNumber);
        if(droneEntity==null){
            throw  new GlobleException("Drone with serial number :"+serialNumber+" dose not exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        return new ResponseDto("Drone Fetched By Serial Number",droneEntity,txnId);
    }

    @Override
    public ResponseDto getAllDroneList(String txnId) {
        return new ResponseDto("Drone List",droneRepo.findAll(),txnId);
    }

    @Override
    public ResponseDto deleteDroneBySerialNumber(String serialNumber, String txnId) throws GlobleException {

        if(droneRepo.countBySerialNumber(serialNumber)==0){
            throw  new GlobleException("Drone with serial number :"+serialNumber+" dose not exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        try {
            droneRepo.deleteBySerialNumber(serialNumber);
            return new ResponseDto("Drone Delete SuccessFull",true,txnId);
        }catch (Exception e){
            throw new GlobleException(e.getMessage(),HttpStatus.BAD_REQUEST,txnId);
        }
    }

    @Override
    public ResponseDto saveDroneLog(DroneLogDto droneLogDto,String txnId) throws GlobleException {
        DroneEntity droneEntity=droneRepo.findBySerialNumber(droneLogDto.getSerialNumber());
        if(droneEntity==null){
            throw  new GlobleException("Drone with serial number :"+droneLogDto.getSerialNumber()+" dose not exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        droneEntity.setBattery_capacity(droneLogDto.getBattery_capacity());
        droneEntity.setDroneStatus(DroneStatus.valueOf(droneLogDto.getDroneStatus()));
        DroneLogEntity droneLogEntity=DroneLogEntity.builder()
                .droneStatus(droneEntity.getDroneStatus())
                .battery_capacity(droneLogDto.getBattery_capacity())
                .build();
        droneLogEntity.setCreatedDate(LocalDateTime.now());
      droneLogEntity=  droneLogRepo.save(droneLogEntity);
        List<DroneLogEntity> droneLogEntities=droneEntity.getDrone_log_list();
        droneLogEntities.add(droneLogEntity);
        droneEntity.setDrone_log_list(droneLogEntities);
        droneRepo.save(droneEntity);
        return new ResponseDto("Drone Logs Saved Successful",true,txnId);
    }

    @Override
    public ResponseDto getDroneLogBySerialNumber(String SerialNumber, String txnId) throws GlobleException {
        DroneEntity droneEntity=droneRepo.findBySerialNumber(SerialNumber);
        if(droneEntity==null){
            throw  new GlobleException("Drone with serial number :"+SerialNumber+" dose not exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        return new ResponseDto("Drone Logs",droneEntity.getDrone_log_list(),txnId);
    }

}
