package club.luozhanghua.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 05:01:26
 * @modified By
 */
@SpringBootApplication
@EnableCaching
public class Oauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }
}
