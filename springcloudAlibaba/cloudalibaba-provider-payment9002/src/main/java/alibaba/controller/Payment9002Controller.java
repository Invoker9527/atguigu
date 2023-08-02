package alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author YangRuiHong
 * @Create 2023-03-05 17:41
 * @Description
 */
@RestController
public class Payment9002Controller {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Integer id) {
        return "nacos registry,serverPort: " + serverPort + "\t id:" + id;
    }
}
