package com.twt.dto;

import lombok.Data;

@Data
public class ConfirmDto {
    private String uid;
    private String name;
    private String idcard;
    private Integer isJoin;
    private Integer buy;
    private String[] bedNeed;
    private String wayToJin;
    private String station;

    private Integer isNeedPickUp;
    private String timeToJin;
    private String trainNumber;
    private Integer accompanyNumber;

}
