package com.twt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class HomeNoticeVO implements Serializable {
    private Integer id;

    private String title;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime createdAt;

}
