package com.twt.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sxd
 * @since 2022-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Apply implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String name;

    private Integer gender;

    private String identity;

    private Integer partyWill;

    private String major;

    private String identityDetail;

    private String nation;

    private String nativePlace;

    private String phone;

    private String qq;

    private String idcard;

    private String email;

    private String fromPlace;

    private String highSchool;

    private String household;

    private String score;

    private String highSchoolExp;

    private String highSchoolHonour;

    private String clothesSize;

    private String hobby;

    private String introduction;

    private String birthDate;

    private String photoPath;

    private String filePath;

    private Integer admit;

    private Integer year;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
