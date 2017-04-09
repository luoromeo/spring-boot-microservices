package club.luozhanghua.app;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhanghua.luo on 3/28/17.
 */
@FeignClient(value = "demo", fallback = DemoClientHystrix.class)
public interface DemoClient {
    @RequestMapping(method = RequestMethod.GET, value = "/welcome")
    String welcome();
}
