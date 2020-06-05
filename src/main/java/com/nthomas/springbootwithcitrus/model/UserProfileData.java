package com.nthomas.springbootwithcitrus.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserProfileData {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
}
