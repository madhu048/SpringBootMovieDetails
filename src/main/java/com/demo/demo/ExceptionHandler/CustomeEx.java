package com.demo.demo.ExceptionHandler;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomeEx {

    public String statusMessage;
    public int statusCode;
    public String errorMessage;
    public LocalDateTime timeStamp;

}
