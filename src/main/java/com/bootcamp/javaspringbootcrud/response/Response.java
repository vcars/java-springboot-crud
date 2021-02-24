package com.bootcamp.javaspringbootcrud.response;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Data
@Component
public class Response {

    private Object data;
    private String message;
    private Boolean result;

    public Response() {


    }

    public Response(String message, Boolean result ,Object data){
        this.message = message;
        this.result = result;
        this.data = data;
    }

    public void doThrowResponseException(HttpStatus httpStatus, String message){
        throw new ResponseStatusException(httpStatus, message);
    }

}
