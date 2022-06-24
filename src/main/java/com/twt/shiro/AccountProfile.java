package com.twt.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义用户对象
 */
@Data
public class AccountProfile implements Serializable {
    private Integer id;
    private String name;
}
