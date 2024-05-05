package com.devteria.identityservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
	private  String id;
	private  String username;
	private String firstName;
	private  String lastName;
	private LocalDate dob;
	private Set<RoleResponse> roles;
}
