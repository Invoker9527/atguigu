package com.atguigu.pojo;

/**
 * @Author YangRuiHong
 * @Create 2023-02-23 17:40
 * @Description
 */

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String userName;
    private String pwd;
}
