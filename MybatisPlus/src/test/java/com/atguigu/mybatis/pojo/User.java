package com.atguigu.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author YangRuiHong
 * @create 2022-09-16 22:03
 * @email Yangrhd@dcits.com
 * @description:
 */
@Data
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String email;


}
