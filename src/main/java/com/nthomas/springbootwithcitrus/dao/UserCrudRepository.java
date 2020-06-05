package com.nthomas.springbootwithcitrus.dao;

import com.nthomas.springbootwithcitrus.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCrudRepository extends JpaRepository<UserProfile, Long> {
    public Optional<UserProfile> findOneByEmail(String email);
}
