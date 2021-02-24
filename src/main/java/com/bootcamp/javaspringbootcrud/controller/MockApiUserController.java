package com.bootcamp.javaspringbootcrud.controller;

import com.bootcamp.javaspringbootcrud.dto.MockApiUserDto;
import com.bootcamp.javaspringbootcrud.request.MockApiUserRequest;
import com.bootcamp.javaspringbootcrud.response.Response;
import com.bootcamp.javaspringbootcrud.service.MockApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/mockapi/user")
public class MockApiUserController {

    @Autowired
    Response response;

    @Autowired
    MockApiUserService mockApiUserService;

    @GetMapping("/v1/list-user")
    public ResponseEntity<Response> getAllMockApiUser() {
        List<MockApiUserDto> result = mockApiUserService.getAllMockApiUser();
        response = new Response("Success Get List MockApi User",true , result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/v1/detail/{id}")
    public ResponseEntity<Response> getDetailMockApiUser(@PathVariable String id){
        MockApiUserDto result = mockApiUserService.getDetailMockApiUser(id);
        response = new Response("Success Get Detail MockApi User",true , result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/create")
    public ResponseEntity<Response> createMockeApiUser(@RequestBody MockApiUserRequest request) {

        mockApiUserService.createMockApiUser(request);

        response = new Response("Success Get Create MockApi User",true , true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<Response> deleteMockApiUser(@PathVariable String id){
        mockApiUserService.deleteMockApiUser(id);
        response = new Response("Success Delete MockApi User",true , true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/v1/update")
    public ResponseEntity<Response> updateMockApiUser(@RequestBody MockApiUserRequest request){
        mockApiUserService.updateMockApiUser(request);
        response = new Response("Success Get Update MockApi User",true , true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
