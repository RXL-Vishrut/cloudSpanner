package com.example.cloudSpanner.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

    private Object data;
    private String message;
    private HttpStatus responseCode;
}