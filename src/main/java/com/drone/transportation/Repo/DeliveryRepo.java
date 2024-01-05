package com.drone.transportation.Repo;

import com.drone.transportation.Entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepo extends JpaRepository<DeliveryEntity,String> {
    int countByDeliveryID(String deliveryID);
    DeliveryEntity findByDeliveryID(String deliveryID);

    List<DeliveryEntity> findAllByDrone_SerialNumber(String serialNumber);

}
