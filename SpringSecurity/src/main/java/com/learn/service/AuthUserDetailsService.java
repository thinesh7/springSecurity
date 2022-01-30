package com.learn.service;

import com.learn.entity.UserAuth;
import com.learn.repository.UserAuthRepository;
import com.learn.bean.AuthUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Author  : Thinesh
 * Version : V1
 */

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuth> user = userAuthRepository.findByUserName(username);
        return user.map(AuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " UserName Not Found in the System"));
    }
}
