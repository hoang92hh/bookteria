package com.devteria.profile.dto.request;

import java.time.LocalDate;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileCreationRequest {
    private String userId;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String city;
}
