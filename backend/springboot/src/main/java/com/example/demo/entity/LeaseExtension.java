package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 延长租赁周期表
 *
 * </p>
 *
 * @author cj
 * @since 2021-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LeaseExtension implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "lease_id", type = IdType.AUTO)
    private Integer leaseId;

    private Integer orderId;

    private Integer dayNumber;

    private Integer rentAdded;


}
