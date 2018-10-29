package club.luozhanghua.oauth2.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月26日 19:15
 * @modified By
 */
@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {

    @RequestMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request)
            throws Exception {

        return "oauth_approval";
    }

}
