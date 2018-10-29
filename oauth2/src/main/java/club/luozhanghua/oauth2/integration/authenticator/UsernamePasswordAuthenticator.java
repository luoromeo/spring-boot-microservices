package club.luozhanghua.oauth2.integration.authenticator;

import club.luozhanghua.oauth2.domain.user.User;
import club.luozhanghua.oauth2.domain.user.UserRepository;
import club.luozhanghua.oauth2.integration.IntegrationAuthentication;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 默认登录处理
 * @author LIQIU
 * @date 2018-3-31
 **/
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authenticate(IntegrationAuthentication integrationAuthentication) {
        return userRepository.findByUsername(integrationAuthentication.getUsername());
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }
}
