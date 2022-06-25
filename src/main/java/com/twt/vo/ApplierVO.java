package com.twt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplierVO {

    private Integer uid;

    private String name;

    private String gender;

    private String identity;

    private String partyWill;

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

    private Integer year;

    private String admit;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

}
