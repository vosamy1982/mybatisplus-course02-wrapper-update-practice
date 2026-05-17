package com.hbnu.course02.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("employee")
public class Employee {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String department;

    private Long departmentId;

    private String position;

    private BigDecimal salary;

    private Integer status;

    private LocalDate hireDate;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String description;
}
