package com.drone.transportation.Service;


import com.drone.transportation.DTO.MedicineDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Entity.MedicineEntity;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.MedicineInterface;
import com.drone.transportation.Repo.MedicineRepo;
import com.drone.transportation.Util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MedicineService implements MedicineInterface {
    private final MedicineRepo medicineRepo;
    @Value("${image.upload.directory}")
    private String uploadDirectory;

    @Autowired
    public MedicineService(MedicineRepo medicineRepo) {
        this.medicineRepo = medicineRepo;
    }


    @Override
    public ResponseDto saveMedicine(MedicineDto medicineDto, MultipartFile file, String txnId) throws GlobleException {
        Mapper mapper=new Mapper();
        if(medicineRepo.countByName(medicineDto.getName())!=0){
            throw  new GlobleException("Medicine with name :"+medicineDto.getName()+" already exist !!", HttpStatus.BAD_REQUEST,txnId);
        }if(medicineRepo.countByCode(medicineDto.getCode())!=0){
            throw  new GlobleException("Medicine with code :"+medicineDto.getCode()+" already exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        String filePath="";
        try{
            filePath =saveFile(file,medicineDto.getCode());
        }catch (Exception e){
            throw  new GlobleException(e.getMessage(), HttpStatus.BAD_REQUEST,txnId);
        }
        return new ResponseDto("Medicine save SuccessFull",medicineRepo.save(mapper.mapMedicineEntity(medicineDto,filePath)),txnId);
    }

    @Override
    public ResponseDto getByCode(String code, String txnId) throws GlobleException {
        MedicineEntity medicineEntity=medicineRepo.findByCode(code);
        if(medicineEntity==null){
            throw  new GlobleException("Medicine with code :"+code+" already exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        return new ResponseDto("Medicine Fetch By Medicine Code",medicineEntity,txnId);
    }

    @Override
    public ResponseDto getByName(String name, String txnId) throws GlobleException {
        MedicineEntity medicineEntity=medicineRepo.findByName(name);
        if(medicineEntity==null){
            throw  new GlobleException("Medicine with name :"+name+" already exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        return new ResponseDto("Medicine Fetch By Medicine Name",medicineEntity,txnId);
    }

    @Override
    public ResponseDto getAllMedicineList(String txnId) throws GlobleException {
        return new ResponseDto("Medicine List",medicineRepo.findAll(),txnId);
    }

    @Override
    public ResponseDto deleteMedicineByCode(String code, String txnId) throws GlobleException {
        if(medicineRepo.countByCode(code)==0){
            throw  new GlobleException("Medicine with code :"+code+" Dose not exist !!", HttpStatus.BAD_REQUEST,txnId);
        }
        try{
            medicineRepo.deleteByCode(code);
        }catch (Exception e){
            throw  new GlobleException(e.getMessage(), HttpStatus.BAD_REQUEST,txnId);
        }
        return new ResponseDto("Medicine Delete SuccessFull",true,txnId);
    }


    private String saveFile(MultipartFile file, String MedicineId) throws IOException {
        String filePath = uploadDirectory + "/" + MedicineId + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());
        return filePath;
    }
}
