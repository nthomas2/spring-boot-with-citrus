package com.nthomas.springbootwithcitrus.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 1000)
    private String email;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    public UserProfile(UserProfileData userProfileData) {
        setEmail(userProfileData.getEmail());
        setFirstName(userProfileData.getFirstName());
        setLastName(userProfileData.getLastName());
    }

    public UserProfileData toProfile() {
        return UserProfileData.builder()
                .id(getId())
                .email(getEmail())
                .firstName(getFirstName())
                .lastName(getLastName())
                .build();
    }
}
