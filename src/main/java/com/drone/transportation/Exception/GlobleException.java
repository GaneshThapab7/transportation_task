package com.drone.transportation.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class GlobleException  extends Exception{

private Object error;
private HttpStatus status;

}
