package com.twt.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义用户对象
 */
@Data
public class AccountProfile implements Serializable {
//    用户id
    private Integer id;
//    学生姓名
    private String name;
}
