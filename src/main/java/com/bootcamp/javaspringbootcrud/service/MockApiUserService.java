package com.bootcamp.javaspringbootcrud.service;

import com.bootcamp.javaspringbootcrud.dto.MockApiUserDto;
import com.bootcamp.javaspringbootcrud.request.MockApiUserRequest;

import java.util.List;

public interface MockApiUserService {

    List<MockApiUserDto> getAllMockApiUser();

    MockApiUserDto getDetailMockApiUser(String id);

    Boolean createMockApiUser(MockApiUserRequest request);

    Boolean deleteMockApiUser(String id);

    Boolean updateMockApiUser(MockApiUserRequest request);

}
