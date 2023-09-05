package com.example.BE_LinkKien.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {
    private String status;
    private int code;
    private String message;
    private Object data;
}
