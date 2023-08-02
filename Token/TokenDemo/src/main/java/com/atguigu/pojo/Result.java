package com.atguigu.pojo;

import lombok.Data;

/**
 * @Author YangRuiHong
 * @Create 2023-02-23 17:38
 * @Description
 */
@Data
public class Result {
    private boolean success;
    private int code;
    private String msg;
    private String data;
}
