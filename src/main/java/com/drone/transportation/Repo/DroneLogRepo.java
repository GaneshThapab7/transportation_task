package com.drone.transportation.Repo;

import com.drone.transportation.Entity.DroneLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneLogRepo extends JpaRepository<DroneLogEntity,String> {


}
