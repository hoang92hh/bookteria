package com.devteria.identityservice.service;


import com.devteria.identityservice.dto.request.UserCreationRequest;
import com.devteria.identityservice.dto.response.UserResponse;
import com.devteria.identityservice.entity.User;
import com.devteria.identityservice.exception.AppException;
import com.devteria.identityservice.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;


    
    private UserCreationRequest request;
    private UserResponse userResponse;
    private User user;
    private LocalDate dob;
    

    //Khoi tao data truoc khi chay test
    @BeforeEach
    private void initData(){
        dob = LocalDate.of(1990,1,1 );

        //declare value of input parameter
        request = UserCreationRequest.builder()
                .username("john")
                .firstName("John")
                .lastName("Done")
                .password("12345789")
                .dob(dob)
                .build();

        userResponse = UserResponse.builder()
                .id("3eb414a8-d533-4222-aa3c-a33a2f369a3b")
                .username("john").firstName("John")
                .lastName("Done")
                .dob(dob)
                .build();


        //declare value of expect result when save succsess
        user = User.builder()
                .id("3eb414a8-d533-4222-aa3c-a33a2f369a3b")
                .username("john").firstName("John")
                .lastName("Done")
//                .password("123456789")
                .dob(dob)
                .build();
    }

    @Test
    void createUser_validRequest_success(){
        //GIVEN

        /* specify data which  mockbean will excute and return expect data  */
        when(userRepository.existsByUsername(anyString())).thenReturn(false); // chua co user ton tai, pass b1
        when(userRepository.save(any())).thenReturn(user); // save success and return 1 object user.

        //WHEN
        /* dung method thuc thi service can test , voi method va param xac dinh, tuy nhien chua ro gia tri tra ve la se gi */
       UserResponse response= userService.createUser(request);


       //THEN
        /*So sanh gia tri tra ve chua biet khi excute service voi gia tri expect ma mockbean tra ve */
        Assertions.assertThat(response.getId()).isEqualTo("3eb414a8-d533-4222-aa3c-a33a2f369a3b");
        Assertions.assertThat(response.getUsername()).isEqualTo("john");

    }

    @Test
    void createUser_userExisted_fail(){
        //GIVEN
        /* specify data which  mockbean will excute and return expect data  */
        when(userRepository.existsByUsername(anyString())).thenReturn(true); // Da ton tai user trong database, khong qua buoc 1, can nem exception.

        //WHEN
         /*Nem exception tu phia service test voi loai exception da duoc khai bao, tuy nhien gia tri thi chua biet */
        var exception = assertThrows(AppException.class, ()-> userService.createUser(request));

        //THEN
       /*So sanh cac param cua exception  duoc tra ra tu phia service, so voi gia tri exception duoc setting (expect result) o Globlal exception */
        Assertions.assertThat(exception.getErrorCode().getCode())
                .isEqualTo(1002);
    }

}
