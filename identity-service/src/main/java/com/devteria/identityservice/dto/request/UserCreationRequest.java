package com.devteria.identityservice.dto.request;

import com.devteria.identityservice.validation.dob.DobConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @Size(min = 4,message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String password;
    private  String firstName;
    private String lastName;

    @DobConstraint(min =20, message = "INVALID_DOB")
    private  LocalDate dob;

    private List<String> roles;
}
