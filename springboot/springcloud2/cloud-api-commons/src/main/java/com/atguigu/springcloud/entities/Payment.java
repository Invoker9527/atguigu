package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @Author YangRuiHong
 * @Create 2022-02-03 18:52
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class Payment implements Serializable {
    private long id;
    private String serial;
}
