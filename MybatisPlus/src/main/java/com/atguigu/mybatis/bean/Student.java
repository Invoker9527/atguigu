package com.atguigu.mybatis.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YangRuiHong
 * @create 2022-07-20 8:55
 * @email Yangrhd@dcits.com
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "t_student")
public class Student {
    @TableId(value = "id", type = IdType.NONE)
    private Long uid;
    @TableField("name")
    private String s_name;
    private String sex;
    private Integer age;
    @TableLogic
    private Integer is_deleted;
}
