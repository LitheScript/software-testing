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
 *
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FavouriteCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "fav_id", type = IdType.AUTO)
    private Integer favId;

    private Integer userId;

    private String name;

    private String description;

    private LocalDateTime time;

    private String coverimg;


}
