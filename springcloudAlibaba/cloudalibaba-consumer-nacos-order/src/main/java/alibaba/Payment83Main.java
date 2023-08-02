package alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author YangRuiHong
 * @Create 2023-03-05 17:41
 * @Description
 */
@EnableDiscoveryClient
@SpringBootApplication
public class Payment83Main {
    public static void main(String[] args) {
        SpringApplication.run(Payment83Main.class, args);
    }

}
