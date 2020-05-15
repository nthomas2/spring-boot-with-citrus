package com.nthomas.springbootwithcitrus.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class UserProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 1000)
    private String email;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(nullable = false, length = 100)
    private String lastName;

    public UserProfileEntity(UserProfile userProfile) {
        setEmail(userProfile.getEmail());
        setFirstName(userProfile.getFirstName());
        setLastName(userProfile.getLastName());
    }

    public UserProfile toProfile() {
        return UserProfile.builder()
                .id(getId())
                .email(getEmail())
                .firstName(getFirstName())
                .lastName(getLastName())
                .build();
    }
}
