package com.nthomas.springbootwithcitrus.service;

import com.nthomas.springbootwithcitrus.dao.UserCrudRepository;
import com.nthomas.springbootwithcitrus.model.UserException;
import com.nthomas.springbootwithcitrus.model.UserProfile;
import com.nthomas.springbootwithcitrus.model.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    public UserProfile createUser(UserProfile userProfile) {
        if (Objects.isNull(userProfile)) {
            throw new UserException("Did not receive user data");
        }
        if (Objects.nonNull(userProfile.getId())) {
            throw new UserException("Cannot create user with existing id");
        }
        Optional<UserProfileEntity> existingUser = userCrudRepository.findOneByEmail(userProfile.getEmail());
        if (existingUser.isPresent()) {
            throw new UserException("User email already exists");
        }

        return userCrudRepository.save(new UserProfileEntity(userProfile)).toProfile();
    }

    public UserProfile getByEmail(String email) {
        Optional<UserProfileEntity> user = userCrudRepository.findOneByEmail(email);
        if (user.isEmpty()) {
            throw new UserException("Could not find user");
        }

        return user.get().toProfile();
    }
}
