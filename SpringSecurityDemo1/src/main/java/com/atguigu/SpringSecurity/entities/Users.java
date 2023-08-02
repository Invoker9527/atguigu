package com.atguigu.SpringSecurity.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author YangRuiHong
 * @create 2022-07-28 10:45
 * @email Yangrhd@dcits.com
 * @description:
 */
@TableName("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private Integer id;
    private String username;
    private String password;
}
