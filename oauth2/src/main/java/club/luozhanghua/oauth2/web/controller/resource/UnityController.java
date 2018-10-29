package club.luozhanghua.oauth2.web.controller.resource;

import club.luozhanghua.oauth2.service.IntegrationUserDetailsService;
import club.luozhanghua.oauth2.service.dto.UserJsonDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:57:44
 * @modified By
 */
@Controller
@RequestMapping("/unity/")
public class UnityController {


//    @Autowired
//    private UserService userService;

    @Autowired
    private IntegrationUserDetailsService integrationUserDetailsService;


    @RequestMapping("dashboard")
    public String dashboard() {
        return "unity/dashboard";
    }

    @RequestMapping("user_info")
    @ResponseBody
    public UserJsonDto userInfo() {
        return integrationUserDetailsService.loadCurrentUserJsonDto();
    }

}