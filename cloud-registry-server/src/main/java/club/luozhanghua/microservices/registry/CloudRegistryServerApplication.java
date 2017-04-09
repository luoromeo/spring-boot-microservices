package club.luozhanghua.microservices.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by zhanghua.luo on 3/25/17.
 */
@SpringBootApplication
@EnableEurekaServer
public class CloudRegistryServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudRegistryServerApplication.class);
    }
}
