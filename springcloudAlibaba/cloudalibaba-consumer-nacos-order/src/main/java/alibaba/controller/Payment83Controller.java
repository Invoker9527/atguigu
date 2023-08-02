package alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * @Author YangRuiHong
 * @Create 2023-03-05 17:41
 * @Description
 */
@RestController
public class Payment83Controller {
    @Value("${server.port}")
    private String serverPort;
    @Resource
    public RestTemplate restTemplate;
    @Value("${server-url.nacos-user-service}")
    private String ServerUrl;

    @GetMapping("consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Integer id) {

        return restTemplate.getForObject(ServerUrl + "/payment/nacos/" + id, String.class);
    }
}
