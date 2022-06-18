package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Object implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "object_id", type = IdType.AUTO)
    private Integer objectId;

    private String name;

    private Integer userId;

    private String description;

    private String status;

    private Integer deposit;

    private String tag;

    private Integer rentDaliy;

    private String newLevel;


}
