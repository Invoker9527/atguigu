package org.apache.management.config;

import org.apache.management.entities.CommonResult;
import org.apache.management.entities.DeviceInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author YangRuiHong
 * @Create 2023-03-26 15:13
 * @Description
 */
@Configuration
public class Config {
    @Bean
    public CommonResult<DeviceInfo> CommonResult() {
        return new CommonResult<>();
    }
}
