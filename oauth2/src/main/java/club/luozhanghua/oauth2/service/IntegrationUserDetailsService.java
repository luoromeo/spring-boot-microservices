package club.luozhanghua.oauth2.service;

import club.luozhanghua.oauth2.domain.shared.security.SOSUserDetails;
import club.luozhanghua.oauth2.domain.user.User;
import club.luozhanghua.oauth2.domain.user.UserRepository;
import club.luozhanghua.oauth2.integration.IntegrationAuthentication;
import club.luozhanghua.oauth2.integration.IntegrationAuthenticationContext;
import club.luozhanghua.oauth2.integration.authenticator.IntegrationAuthenticator;
import club.luozhanghua.oauth2.service.dto.UserDto;
import club.luozhanghua.oauth2.service.dto.UserFormDto;
import club.luozhanghua.oauth2.service.dto.UserJsonDto;
import club.luozhanghua.oauth2.service.dto.UserOverviewDto;
import club.luozhanghua.oauth2.web.WebUtils;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 集成认证用户服务
 *
 * @author LIQIU
 * @date 2018-3-7
 **/
@Service("integrationUserDetailsService")
public class IntegrationUserDetailsService implements UserDetailsService {
    private static final Logger LOG = LoggerFactory.getLogger(IntegrationUserDetailsService.class);


    @Autowired
    private UserRepository userRepository;

    private List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        User user = this.authenticate(integrationAuthentication);

        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
//        this.setAuthorize(user);
        return new SOSUserDetails(user);

    }

    /**
     * 设置授权信息
     *
     * @param user
     */
    public void setAuthorize(User user) {
//        Authorize authorize = this.sysAuthorizeClient.getAuthorize(user.getId());
//        user.setRoles(authorize.getRoles());
//        user.setResources(authorize.getResources());
    }

    private User authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }

    @Transactional(readOnly = true)
    public UserJsonDto loadCurrentUserJsonDto() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final Object principal = authentication.getPrincipal();

        if (authentication instanceof OAuth2Authentication &&
                (principal instanceof String || principal instanceof org.springframework.security.core.userdetails.User)) {
            return loadOauthUserJsonDto((OAuth2Authentication) authentication);
        } else {
            final SOSUserDetails userDetails = (SOSUserDetails) principal;
            return new UserJsonDto(userRepository.findByGuid(userDetails.user().guid()));
        }
    }

    @Transactional(readOnly = true)
    public UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto) {
        List<User> users = userRepository.findUsersByUsername(overviewDto.getUsername());
        overviewDto.setUserDtos(UserDto.toDtos(users));
        return overviewDto;
    }

    @Transactional(readOnly = true)
    public boolean isExistedUsername(String username) {
        final User user = userRepository.findByUsername(username);
        return user != null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUser(UserFormDto formDto) {
        User user = formDto.newUser();
        userRepository.saveUser(user);
        LOG.debug("{}|Save User: {}", WebUtils.getIp(), user);
        return user.guid();
    }


    private UserJsonDto loadOauthUserJsonDto(OAuth2Authentication oAuth2Authentication) {
        UserJsonDto userJsonDto = new UserJsonDto();
        userJsonDto.setUsername(oAuth2Authentication.getName());

        final Collection<GrantedAuthority> authorities = oAuth2Authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            userJsonDto.getPrivileges().add(authority.getAuthority());
        }

        return userJsonDto;
    }
}
