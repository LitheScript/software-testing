package com.example.demo.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 关系表
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Fav implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer favId;

    private Integer objectId;

    private LocalDateTime time;


}
