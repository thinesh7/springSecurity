package com.learn.repository;

import com.learn.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByUserName(String username);
}
