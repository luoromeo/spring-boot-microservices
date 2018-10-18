package club.luozhanghua.oauth2.web.controller;


import club.luozhanghua.oauth2.web.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:59:02
 * @modified By
 */
@Controller
public class SOSController {


    private static final Logger LOG = LoggerFactory.getLogger(SOSController.class);


    /**
     * 首页
     */
    @RequestMapping(value = "/")
    public String index(Model model) {
        return "index";
    }


    //Go login
    @GetMapping(value = {"/login"})
    public String login(Model model) {
        LOG.info("Go to login, IP: {}", WebUtils.getIp());
        return "login";
    }


}
