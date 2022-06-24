package com.twt.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Confirm implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private String name;

    private String idcard;

    private Integer isJoin;

    private Integer buy;

    private String wayToJin;

    private String station;

    private String bedNeed;

    private Integer year;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    private Integer isNeedPickUp;

    private String timeToJin;

    private String trainNumber;

    private Integer accompanyNumber;



}
