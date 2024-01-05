package com.drone.transportation.Interface;


import com.drone.transportation.DTO.MedicineDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Exception.GlobleException;
import org.springframework.web.multipart.MultipartFile;

public interface MedicineInterface {

    ResponseDto saveMedicine(MedicineDto medicineDto, MultipartFile file, String txnId) throws GlobleException;
    ResponseDto getByCode(String code,String txnId) throws GlobleException;
    ResponseDto getByName(String name,String txnId) throws GlobleException;
    ResponseDto getAllMedicineList(String txnId) throws GlobleException;
    ResponseDto deleteMedicineByCode(String code,String txnId) throws GlobleException;
}
