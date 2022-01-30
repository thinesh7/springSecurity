package com.learn.entity.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Author  : Thinesh
 * Version : V1
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_AUTH")
public class UserAuth {

    @Id
    @Column(name = "ID")
    private Long userId;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "ROLE")
    private String role;

}
