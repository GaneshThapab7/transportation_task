package com.drone.transportation.Repo;

import com.drone.transportation.Entity.DroneEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneRepo extends JpaRepository<DroneEntity,String> {

    int countBySerialNumber(String serialNumber);
    DroneEntity findBySerialNumber(String txnId);
    void deleteBySerialNumber(String serialNumber);

    @EntityGraph(attributePaths = {})
    @Query("SELECT p FROM DroneEntity p WHERE p.serialNumber = :serialNumber")
    DroneEntity findSerialNumber(@Param("serialNumber") String serialNumber);


}
