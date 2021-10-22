package com.ipractice.springApi.Schemas;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseSchema {
    private String status;
    private Object data;
}
