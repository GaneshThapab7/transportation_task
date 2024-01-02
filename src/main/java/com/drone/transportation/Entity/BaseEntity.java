package com.drone.transportation.Entity;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class BaseEntity {
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}
