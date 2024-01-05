package com.drone.transportation.Controller;

import com.drone.transportation.DTO.DroneDto;
import com.drone.transportation.DTO.MedicineDto;
import com.drone.transportation.DTO.Request.RequestDto;
import com.drone.transportation.DTO.Response.ResponseDto;
import com.drone.transportation.Exception.GlobleException;
import com.drone.transportation.Interface.MedicineInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping( "/medicine")
public class MedicineController {

    private final MedicineInterface medicineInterface;

    @Autowired
    public MedicineController(MedicineInterface medicineInterface) {
        this.medicineInterface = medicineInterface;
    }


    @GetMapping("/byCode/{code}")
    public ResponseEntity<ResponseDto> getByCode(@PathVariable("code") String code) throws GlobleException {
        return new ResponseEntity(medicineInterface.getByCode(code, UUID.randomUUID().toString()), HttpStatus.OK);
    }
    @GetMapping("/byName/{code}")
    public ResponseEntity<ResponseDto> getByName(@PathVariable("name") String name) throws GlobleException {
        return new ResponseEntity(medicineInterface.getByName(name, UUID.randomUUID().toString()), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<ResponseDto> findAllMedicine() throws GlobleException {
        return new ResponseEntity<>(medicineInterface.getAllMedicineList(UUID.randomUUID().toString()),HttpStatus.OK);
    }
    @DeleteMapping("/{code}")
    public ResponseEntity<ResponseDto> deleteMedicineByCode(@PathVariable("code") String code) throws GlobleException {
        return new ResponseEntity<>(medicineInterface.deleteMedicineByCode(code,UUID.randomUUID().toString()),HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<ResponseDto>saveMedicine(@RequestBody @Valid  RequestDto<MedicineDto> droneDto,
                                                   @RequestPart("file") MultipartFile file) throws GlobleException {
        return new ResponseEntity(medicineInterface.saveMedicine(droneDto.getRequest(),file,droneDto.getTxnId()),HttpStatus.OK);
    }
}
