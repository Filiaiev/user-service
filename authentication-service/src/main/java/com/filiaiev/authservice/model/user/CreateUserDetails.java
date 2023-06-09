package com.filiaiev.authservice.model.user;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class CreateUserDetails {

    private String firstName;
    private String lastName;
    private String middleName;
    private Gender gender;
    private LocalDate dob;
    private String mobile;
    private String city;
    private String country;
    private String addressLine1;
    private String addressLine2;
    private String postcode;
    private Instant createdAt;
}
