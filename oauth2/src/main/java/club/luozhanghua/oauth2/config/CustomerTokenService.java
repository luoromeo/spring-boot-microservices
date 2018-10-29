package club.luozhanghua.oauth2.config;

import club.luozhanghua.oauth2.utils.JwtUtil;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * @description 自定义TokenService
 * @author zhanghua.luo
 * @date 2018年10月28日 21:30
 * @modified By
 */
public class CustomerTokenService extends DefaultTokenServices {
    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken token = super.createAccessToken(authentication);
        String jwtToken = JwtUtil.createJwtToken((UserDetails) authentication.getPrincipal());

        return token;
    }


}
