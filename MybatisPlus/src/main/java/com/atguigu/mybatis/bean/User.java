package com.atguigu.mybatis.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * @author YangRuiHong
 * @create 2022-07-19 10:49
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@TableName("t_user")//用于设置实体类对应的表名
public class User {
    //将这个注解对应的字段作为主键,value属性用于指定主键的字段,type设置主键生成策略，默认为雪花算法生成，IdType.AUTO为自动递增（需在数据库设置该字段为自动递增）
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;
    //指定属性对应的数据库字段名
    @TableField("user_name")
    private String Name;
    private Integer age;
    private String email;
    //逻辑删除字段
    @TableLogic
    private Integer isDeleted;
}

