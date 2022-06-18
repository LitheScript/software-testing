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
 * 发帖的表
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    private Integer userId;

    private String title;

    private String content;

    private LocalDateTime time;

    private Integer expectedPrice;


}
