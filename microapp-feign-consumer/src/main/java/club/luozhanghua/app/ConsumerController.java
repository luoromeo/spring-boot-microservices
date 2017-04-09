package club.luozhanghua.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanghua.luo on 3/28/17.
 */
@RestController
public class ConsumerController {
    @Autowired
    DemoClient demoClient;
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add() {
        return demoClient.welcome();
    }
}
