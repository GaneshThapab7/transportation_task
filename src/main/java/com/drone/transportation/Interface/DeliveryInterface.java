package com.drone.transportation.Interface;

import com.drone.transportation.DTO.DeliveryDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Exception.GlobleException;

public interface DeliveryInterface {

    ResponseDto createDelivery(DeliveryDto deliveryDto,String txnId) throws GlobleException;
    ResponseDto getDeliveryByDeliveryID(String deliveryID,String txnId) throws GlobleException;
    ResponseDto getDeliveryByDroneID(String droneIdSerialNumber,String txnId) throws GlobleException;
}
