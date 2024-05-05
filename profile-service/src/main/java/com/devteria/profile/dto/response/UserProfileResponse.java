package com.devteria.profile.dto.response;

import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfileResponse {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String city;
}
