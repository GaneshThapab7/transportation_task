package com.drone.transportation.Repo;

import com.drone.transportation.Entity.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepo extends JpaRepository<MedicineEntity,String> {
    int countByName(String name);
    int countByCode(String code);
    MedicineEntity findByName(String name);
    MedicineEntity findByCode(String code);
    void deleteByCode(String code);
}
