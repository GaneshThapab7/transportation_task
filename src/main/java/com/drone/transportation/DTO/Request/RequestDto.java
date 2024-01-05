package com.drone.transportation.DTO.Request;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class RequestDto<T> {

    private String txnId= UUID.randomUUID().toString();
    private T request;
    private List<T> requestlist;

}
