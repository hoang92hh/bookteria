package com.devteria.identityservice.controller;


import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@AutoConfigureMockMvc
@Slf4j
@SpringBootTest /*init framework Unitest*/
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    //kha bao dau vao cua function test
    private UserCreationRequest request;

    //kha bao dau ra cua function test
    private UserResponse userResponse;


    //Khoi tao data truoc khi chay test
    @BeforeEach
    private void initData(){
        LocalDate dob = LocalDate.of(1990,1,1 );

        request = UserCreationRequest.builder()
                .username("john").firstName("John").lastName("Done").password("12345789").dob(dob).build();

        userResponse = UserResponse.builder()
                .id("3eb414a8-d533-4222-aa3c-a33a2f369a3b")
                .username("john").firstName("John")
                .lastName("Done")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success() throws Exception {
        //GIVEN : du lieu dau vao ma chung ta da biet truoc,
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule( new JavaTimeModule()); // LocalDate to String
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(userService.createUser(ArgumentMatchers.any())).thenReturn(userResponse);


        //WHEN  : Khi nao request duoc thuc hien, theo dieu kien gi
        //THEN : Ket qua mong muon, tuong ung voi dau vao va dieu kien

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1000))
                .andExpect(MockMvcResultMatchers.jsonPath("result.id").value("3eb414a8-d533-4222-aa3c-a33a2f369a3b"));

    }

    @Test
    void createUser_usernameInvalid_fail() throws Exception {
        //GIVEN : du lieu dau vao ma chung ta da biet truoc,

        request.setUsername("joh");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule( new JavaTimeModule()); // LocalDate to String
        String content = objectMapper.writeValueAsString(request);




        //WHEN  : Khi nao request duoc thuc hien, theo dieu kien gi
        //THEN : Ket qua mong muon, tuong ung voi dau vao va dieu kien

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(1003))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Username must be at least 4 characters"));

    }

}
