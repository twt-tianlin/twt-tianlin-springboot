package com.twt.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {

    private String name;

    private String email;

    private String password;
}
