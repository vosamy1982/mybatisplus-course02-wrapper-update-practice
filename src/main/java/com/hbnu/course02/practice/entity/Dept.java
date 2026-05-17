package com.hbnu.course02.practice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("dept")
public class Dept {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long companyId;

    private Integer active;
}
