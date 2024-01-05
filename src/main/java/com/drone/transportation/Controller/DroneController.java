package com.drone.transportation.Controller;

import com.drone.transportation.DTO.DroneDto;
import com.drone.transportation.DTO.DroneLogDto;
import com.drone.transportation.DTO.Request.RequestDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.DroneInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( "/drone")
public class DroneController {

    private final DroneInterface droneInterface;

    @Autowired
    public DroneController(DroneInterface droneInterface) {
        this.droneInterface = droneInterface;
    }

    @GetMapping("/{SerialNumber}")
    public ResponseEntity<ResponseDto>getBySerialNumber(@PathVariable("SerialNumber") String serialNumber) throws GlobleException {
        return new ResponseEntity(droneInterface.getBySerialNumber(serialNumber, UUID.randomUUID().toString()), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseDto> findAllDrone() throws GlobleException {
        return new ResponseEntity<>(droneInterface.getAllDroneList(UUID.randomUUID().toString()),HttpStatus.OK);
    }
    @DeleteMapping("/{SerialNumber}")
    public ResponseEntity<ResponseDto> deleteDroneBYSerialNumber(@PathVariable("SerialNumber") String serialNumber) throws GlobleException {
        return new ResponseEntity<>(droneInterface.deleteDroneBySerialNumber(serialNumber,UUID.randomUUID().toString()),HttpStatus.OK);
    }
   @PostMapping("/save")
    public ResponseEntity<ResponseDto>saveDrone(@RequestBody RequestDto<DroneDto> droneDto) throws GlobleException {
        return new ResponseEntity(droneInterface.saveDrone(droneDto.getRequest(),droneDto.getTxnId()),HttpStatus.OK);
   }
   @GetMapping("/droneLog/{SerialNumber}")
    public ResponseEntity<ResponseDto>getDroneLogBySerialNumber(@PathVariable("SerialNumber")String SerialNumber) throws GlobleException{
        return new ResponseEntity(droneInterface.getDroneLogBySerialNumber(SerialNumber,UUID.randomUUID().toString()),HttpStatus.OK);
   }
   @PostMapping("/saveLog")
   public ResponseEntity<ResponseDto>saveDroneLOg(@RequestBody RequestDto<DroneLogDto> droneDto) throws GlobleException {
       return new ResponseEntity(droneInterface.saveDroneLog(droneDto.getRequest(),droneDto.getTxnId()),HttpStatus.OK);
   }
}
