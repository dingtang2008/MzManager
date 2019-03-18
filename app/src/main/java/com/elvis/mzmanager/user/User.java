package com.elvis.mzmanager.user;

import com.idogfooding.backbone.network.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type User.
 * no info currently .
 * todo
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class User extends BaseEntity {
    //fake data
    String username;
    //fake data
    String password;
}
