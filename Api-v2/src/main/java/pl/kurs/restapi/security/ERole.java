package pl.kurs.restapi.security;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


public enum ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN
}

