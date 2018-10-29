package club.luozhanghua.oauth2.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月28日 21:30
 * @modified By
 */
public class CustomerTokenService extends DefaultTokenServices {
    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken token = super.createAccessToken(authentication);
        System.out.println(token.getValue());
        System.out.println("ready to save token");
        //获取用户信息
        authentication.getPrincipal();
        return token;
    }
}
