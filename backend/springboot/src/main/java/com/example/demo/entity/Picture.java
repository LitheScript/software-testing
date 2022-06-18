package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 图片表
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "picture_id", type = IdType.AUTO)
    private Integer pictureId;

    private Integer type;

    private Integer objectId;

    private String pictureUrl;


}
