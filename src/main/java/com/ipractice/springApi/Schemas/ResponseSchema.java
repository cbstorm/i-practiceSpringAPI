package com.ipractice.springApi.Schemas;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSchema {
    private String status;
    private Object data;
}
