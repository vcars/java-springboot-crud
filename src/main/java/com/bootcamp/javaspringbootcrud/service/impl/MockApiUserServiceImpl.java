package com.bootcamp.javaspringbootcrud.service.impl;

import com.bootcamp.javaspringbootcrud.constant.Constant;
import com.bootcamp.javaspringbootcrud.dto.MockApiUserDto;
import com.bootcamp.javaspringbootcrud.request.MockApiUserRequest;
import com.bootcamp.javaspringbootcrud.service.MockApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
public class MockApiUserServiceImpl implements MockApiUserService {

    @Value("${mockapi.url}")
    private String resourceUrl;

    @Autowired
    RestTemplate restTemplate;


    @Override
    public List<MockApiUserDto> getAllMockApiUser() {
        ResponseEntity<List<MockApiUserDto>> response = restTemplate.exchange(resourceUrl,HttpMethod.GET,null,new ParameterizedTypeReference<List<MockApiUserDto>>() {
        });

        if(response.getBody() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND , Constant.DATA_NOT_FOUND);
        }

        return response.getBody();
    }

    @Override
    public MockApiUserDto getDetailMockApiUser(String id) {
        MockApiUserDto response = restTemplate.getForObject(resourceUrl+'/'+id ,MockApiUserDto.class);

        if(response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, Constant.DATA_NOT_FOUND);
        }

        return response;
    }

    @Override
    public Boolean createMockApiUser(MockApiUserRequest request) {
        this.doValidate(request);

        try {
            restTemplate.postForEntity(resourceUrl,request,MockApiUserDto.class);
            return true;
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Failed Submit Data");
        }


    }

    @Override
    public Boolean deleteMockApiUser(String id) {
        MockApiUserDto response = getDetailMockApiUser(id);

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,Constant.DATA_NOT_FOUND);
        }

        try {
            restTemplate.delete(resourceUrl+'/'+id);
            return true;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Failed Delete Data");
        }


    }

    @Override
    public Boolean updateMockApiUser(MockApiUserRequest request) {
        doValidate(request);

        if (request.getId().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is Mandatory");
        }

        MockApiUserDto response = getDetailMockApiUser(request.getId());

        if (request.getFirstName().isEmpty() || request.getFirstName() == null) {
            request.setFirstName(response.getFirstName());
        }

        if (request.getLastName().isEmpty() || request.getLastName() == null) {
            request.setLastName(response.getLastName());
        }

        try {
            restTemplate.put(resourceUrl+"/"+request.getId() ,MockApiUserDto.class);
            return true;
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR , "Failed Update Data");
        }
    }

    private void doValidate(MockApiUserRequest request){
        if (request.getFirstName().isEmpty() || request.getFirstName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Firstname Mandatory");
        }


        if (request.getLastName().isEmpty() || request.getLastName() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Lastname Mandatory");
        }
    }
}