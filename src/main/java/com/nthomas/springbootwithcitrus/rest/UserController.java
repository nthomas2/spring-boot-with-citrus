package com.nthomas.springbootwithcitrus.rest;

import com.nthomas.springbootwithcitrus.model.UserProfileData;
import com.nthomas.springbootwithcitrus.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "User", tags = "Rest")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/user")
    public UserProfileData register(@RequestBody UserProfileData userProfileData) {
        return userService.createUser(userProfileData);
    }

    @GetMapping("/user/{email}")
    public UserProfileData fetch(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }
}
