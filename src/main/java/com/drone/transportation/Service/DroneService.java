package com.drone.transportation.Service;

import com.drone.transportation.DTO.DroneDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Entity.DroneEntity;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.DroneInterface;
import com.drone.transportation.Repo.DroneRepo;
import com.drone.transportation.Util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DroneService implements DroneInterface {

    private final DroneRepo droneRepo;
    private Mapper mapper=new Mapper();

    @Override
    public ResponseDto saveDrone(DroneDto droneDto,String txnId) throws GlobleException {
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
    public ResponseDto saveDroneLog(String txnId) {
        return null;
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
