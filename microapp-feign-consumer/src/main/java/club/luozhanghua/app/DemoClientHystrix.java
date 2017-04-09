package club.luozhanghua.app;

import org.springframework.stereotype.Component;

/**
 * Created by zhanghua.luo on 4/5/17.
 */
@Component
public class DemoClientHystrix implements DemoClient{
    @Override
    public String welcome() {
        return "9999";
    }
}
