package com.drone.transportation.Controller;

import com.drone.transportation.DTO.DeliveryDto;
import com.drone.transportation.DTO.Request.RequestDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.DeliveryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping( "/delivery")
public class DeliveryController {

    private final DeliveryInterface deliveryInterface;

    @Autowired
    public DeliveryController(DeliveryInterface deliveryInterface) {
        this.deliveryInterface = deliveryInterface;
    }
    @GetMapping("/{DeliveryID}")
    public ResponseEntity<ResponseDto> getDeliveryByDeliveryID(@PathVariable("DeliveryID") String DeliveryID) throws GlobleException {
        return new ResponseEntity(deliveryInterface.getDeliveryByDeliveryID(DeliveryID, UUID.randomUUID().toString()), HttpStatus.OK);
    }
    @GetMapping("/Drone/{DroneID}")
    public ResponseEntity<ResponseDto> getDeliveryByDroneID(@PathVariable("DroneID") String DroneID) throws GlobleException {
        return new ResponseEntity(deliveryInterface.getDeliveryByDroneID(DroneID, UUID.randomUUID().toString()), HttpStatus.OK);
    }
    @PostMapping("/all")
    public ResponseEntity<ResponseDto> saveDelivery(@RequestBody RequestDto<DeliveryDto> dtoRequestDto) throws GlobleException {
        return new ResponseEntity<>(deliveryInterface.createDelivery(dtoRequestDto.getRequest(),dtoRequestDto.getTxnId()),HttpStatus.OK);
    }

}
