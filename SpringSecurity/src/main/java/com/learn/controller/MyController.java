package com.learn.controller;

import com.learn.entity.UserAuth;
import com.learn.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("rest/v1/cib")
public class MyController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    /**
     * If logged in user is ADMIN -> ADMIN OR MODERATOR
     * If logged in user is MODERATOR -> MODERATOR
     */

    @PutMapping("/access/{userId}/{userRole}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_MODERATOR')")
    public String grantRoleToUser(@PathVariable Long userId, @PathVariable String userRole, Principal principal) {
        String msg = "";
        List<String> activeRoles = getRolesOfByLoggedInUser(principal);
        if (activeRoles.contains(userRole)) {
            UserAuth userAuth = userAuthRepository.findById(userId).get();
            userAuth.setRoles(userAuth.getRoles() + "," + userRole);
            userAuthRepository.save(userAuth);
            msg = userAuth.getUserName() + " Role : " + userRole + " has been Provided..!";
        } else {
            msg = principal.getName() + " doesn't have authority to give access ..!";
        }
        return msg;
    }

    @GetMapping("/adminTest")
    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String testAdminAccess() {
        return "Hello ..! I have ROLE_ADMIN access";
    }

    @GetMapping("/moderatorTest")
    @Secured("ROLE_MODERATOR")
    @PreAuthorize("hasAuthority('ROLE_MODERATOR')")
    public String testModeratorAccess() {
        return "Hello ..! I have ROLE_MODERATOR access";
    }

    @GetMapping("/userTest")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String testUserAccess() {
        return "Hello ..! I have USER Access..!";
    }

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<UserAuth> getAllUsers() {
        return userAuthRepository.findAll();
    }

    private List<String> getRolesOfByLoggedInUser(Principal principal) {
        String roles = userAuthRepository.findByUserName(principal.getName()).get().getRoles();
        if (roles.contains("ROLE_ADMIN")) {
            return Arrays.stream(new String[]{"ROLE_ADMIN", "ROLE_MODERATOR"}).collect(Collectors.toList());
        } else if (roles.contains("ROLE_MODERATOR")) {
            return Arrays.stream(new String[]{"ROLE_MODERATOR"}).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
