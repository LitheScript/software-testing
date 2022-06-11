package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 评价表
 *
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Remarks implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "remark_id", type = IdType.AUTO)
    private Integer remarkId;

    private Integer userId;

    private LocalDateTime time;

    private String content;

    private Integer grade;

    private Integer type;

    private Integer objectId;


}
