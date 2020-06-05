package com.nthomas.springbootwithcitrus.service;

import com.nthomas.springbootwithcitrus.dao.UserCrudRepository;
import com.nthomas.springbootwithcitrus.model.UserException;
import com.nthomas.springbootwithcitrus.model.UserProfileData;
import com.nthomas.springbootwithcitrus.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserCrudRepository userCrudRepository;

    @Autowired
    public UserService(final UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public UserProfileData createUser(UserProfileData userProfileData) {
        if (Objects.isNull(userProfileData)) {
            throw new UserException("Did not receive user data");
        }
        if (Objects.nonNull(userProfileData.getId())) {
            throw new UserException("Cannot create user with existing id");
        }
        Optional<UserProfile> existingUser = userCrudRepository.findOneByEmail(userProfileData.getEmail());
        if (existingUser.isPresent()) {
            throw new UserException("User email already exists");
        }

        return userCrudRepository.save(new UserProfile(userProfileData)).toProfile();
    }

    public UserProfileData getByEmail(String email) {
        Optional<UserProfile> user = userCrudRepository.findOneByEmail(email);
        if (user.isEmpty()) {
            throw new UserException(HttpStatus.NOT_FOUND, "Could not find user");
        }

        return user.get().toProfile();
    }
}
