package com.ita8.m2.loyolmi2inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user_list")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="username", unique = true, nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    public UserAccount() {
    }
}
