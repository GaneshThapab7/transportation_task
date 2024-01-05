package com.drone.transportation.Interface;

import com.drone.transportation.DTO.DroneDto;
import com.drone.transportation.DTO.DroneLogDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Exception.GlobleException;

public interface DroneInterface {

    ResponseDto saveDrone(DroneDto droneDto,String txnId) throws GlobleException;
    ResponseDto getBySerialNumber(String serialNumber,String txnId) throws GlobleException;
    ResponseDto getAllDroneList(String txnId) throws GlobleException;
    ResponseDto deleteDroneBySerialNumber(String serialNumber,String txnId) throws GlobleException;
    ResponseDto saveDroneLog(DroneLogDto droneLogDto, String txnId) throws GlobleException;
    ResponseDto getDroneLogBySerialNumber(String SerialNumber,String txnId) throws GlobleException;

}
