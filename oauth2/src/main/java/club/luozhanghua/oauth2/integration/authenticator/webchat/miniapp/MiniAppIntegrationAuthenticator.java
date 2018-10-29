package club.luozhanghua.oauth2.integration.authenticator.webchat.miniapp;

import club.luozhanghua.oauth2.domain.user.User;
import club.luozhanghua.oauth2.domain.user.UserRepository;
import club.luozhanghua.oauth2.integration.IntegrationAuthentication;
import club.luozhanghua.oauth2.integration.authenticator.IntegrationAuthenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

/**
 * 小程序集成认证
 *
 * @author LIQIU
 * @date 2018-3-31
 **/
@Service
public class MiniAppIntegrationAuthenticator implements IntegrationAuthenticator {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private WxMaService wxMaService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User authenticate(IntegrationAuthentication integrationAuthentication) {
        WxMaJscode2SessionResult session = null;
        String password = integrationAuthentication.getAuthParameter("password");
        //            session = this.wxMaService.getUserService().getSessionInfo(password);
//            WechatMiniAppToken wechatToken = new WechatMiniAppToken(session.getOpenid(), session.getUnionid(), session.getSessionKey());
//            // 加密算法的初始向量
//            wechatToken.setIv(integrationAuthentication.getAuthParameter("iv"));
//            // 用户的加密数据
//            wechatToken.setEncryptedData(integrationAuthentication.getAuthParameter("encryptedData"));
        String openId = session.getOpenid();
        // todo 完善代码
//        User sysUserAuthentication = sysUserClient.findUserBySocial(UcClientConstant.SOCIAL_TYPE_WECHAT_MINIAP, openId);
//        if (sysUserAuthentication != null) {
//            sysUserAuthentication.setPassword(passwordEncoder.encode(password));
//        }
        return new User();
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return "wechatMA".equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
