package com.learn.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "USERNAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
    @Column(name = "ROLE")
    private String roles;

}
