package com.nthomas.springbootwithcitrus.dao;

import com.nthomas.springbootwithcitrus.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCrudRepository extends JpaRepository<UserProfileEntity, Long> {
    public Optional<UserProfileEntity> findOneByEmail(String email);
}
