package com.drone.transportation.enms;

import com.drone.transportation.Exception.GlobleException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum DroneModel {
    Lightweight("light weight",100.05F),Middleweight("middle weight",200.00F),Cruiserweight("cruiser weight",250.00F),Heavyweight("heavy weight",500.00F);

    private String name;
    private Float maxWeightLimit;


    public static DroneModel getByName(String name) throws GlobleException {
        if (name == null)
            throw new IllegalArgumentException("Drone Name cannot be null");
        DroneModel[] Actions = values();
        for (DroneModel droneModel : Actions) {
            if (name.equals(droneModel.name)) {
                return droneModel;
            }
        }
        throw new GlobleException("Invalid Drone Module "+name, HttpStatus.BAD_REQUEST);
    }

}
