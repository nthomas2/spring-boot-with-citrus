package com.nthomas.springbootwithcitrus.rest;

import com.nthomas.springbootwithcitrus.model.UserProfile;
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
    public UserProfile register(@RequestBody UserProfile userProfile) {
        return userService.createUser(userProfile);
    }

    @GetMapping("/user/{email}")
    public UserProfile fetch(@PathVariable("email") String email) {
        return userService.getByEmail(email);
    }
}
