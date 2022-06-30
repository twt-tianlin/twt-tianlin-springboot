package com.twt.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeVO {
    private Integer id;
    private String title;
    private String content;
    private String filePath;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDateTime updatedAt;
}
