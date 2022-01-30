package com.learn.controller;


import com.learn.entity.UserAuth;
import com.learn.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Author  : Thinesh
 * Version : V1
 */

@RestController
@RequestMapping("rest/security")
public class MyAuthController {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * URL: rest/security/joinGroup - POST
     */

    @PostMapping("joinGroup")
    public String joinGroup(@RequestBody UserAuth user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setIsActive(true);
        user.setRoles(DEFAULT_ROLE);
        userAuthRepository.findByUserName(user.getUserName())
                .ifPresent((x) -> new IllegalStateException("User Already Added"));
        userAuthRepository.save(user);
        return user.getUserName() + " has been Added to the Group..! ";
    }

    @GetMapping
    public String welcome() {
        return "Welcome ..! ";
    }
}
