package club.luozhanghua.oauth2.config;


import club.luozhanghua.oauth2.domain.oauth.CustomJdbcClientDetailsService;
import club.luozhanghua.oauth2.service.OauthService;
import club.luozhanghua.oauth2.service.UserService;
import club.luozhanghua.oauth2.web.oauth.OauthUserApprovalHandler;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:45:29
 * @modified By
 */
@Configuration
public class OAuth2ServerConfiguration {


    /*Fixed,  resource-id */
    public static final String RESOURCE_ID = "sos-resource";


    // unity resource
    @Configuration
    @EnableResourceServer
    protected static class UnityResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().antMatchers("/unity/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/unity/**").access("#oauth2.hasScope('read') and hasRole('ROLE_UNITY')");

        }

    }

    // mobile resource
    @Configuration
    @EnableResourceServer
    protected static class MobileResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    // Since we want the protected resources to be accessible in the UI as well we need
                    // session creation to be allowed (it's disabled by default in 2.0.6)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .and()
                    .requestMatchers().antMatchers("/m/**")
                    .and()
                    .authorizeRequests()
                    .antMatchers("/m/**").access("#oauth2.hasScope('read') and hasRole('ROLE_MOBILE')");

        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


        @Autowired
        private TokenStore tokenStore;


        @Autowired
        private ClientDetailsService clientDetailsService;


        @Autowired
        private OauthService oauthService;


        @Autowired
        private AuthorizationCodeServices authorizationCodeServices;


        @Autowired
        private UserService userDetailsService;


        @Autowired
        @Qualifier("authenticationManagerBean")
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private TokenEnhancer jwtTokenEnhancer;


        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients.withClientDetails(clientDetailsService);
        }


//        @Bean
//        public TokenStore tokenStore(RedisConnectionFactory connectionFactory) {
//            return new MyRedisTokenStore(connectionFactory);
//        }


        @Bean
        public ClientDetailsService clientDetailsService(DataSource dataSource) {
            return new CustomJdbcClientDetailsService(dataSource);
        }


        @Bean
        public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
            return new JdbcAuthorizationCodeServices(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.tokenStore(tokenStore)
                    .authorizationCodeServices(authorizationCodeServices)
                    .userDetailsService(userDetailsService)
                    .userApprovalHandler(userApprovalHandler())
//                    .accessTokenConverter(jwtAccessTokenConverter)
//                    .tokenEnhancer(jwtTokenEnhancer)
                    .authenticationManager(authenticationManager);

            //扩展token返回结果
            if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
                TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
                List<TokenEnhancer> enhancerList = new ArrayList<>();
                enhancerList.add(jwtTokenEnhancer);
                enhancerList.add(jwtAccessTokenConverter);
                tokenEnhancerChain.setTokenEnhancers(enhancerList);
                //jwt
                endpoints.tokenEnhancer(tokenEnhancerChain)
                        .accessTokenConverter(jwtAccessTokenConverter);
            }
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//            oauthServer.realm("spring-oauth-server")
//                    .allowFormAuthenticationForClients();
            oauthServer.allowFormAuthenticationForClients();
        }

        @Bean
        public OAuth2RequestFactory oAuth2RequestFactory() {
            return new DefaultOAuth2RequestFactory(clientDetailsService);
        }


        @Bean
        public UserApprovalHandler userApprovalHandler() {
            OauthUserApprovalHandler userApprovalHandler = new OauthUserApprovalHandler();
            userApprovalHandler.setOauthService(oauthService);
            userApprovalHandler.setTokenStore(tokenStore);
            userApprovalHandler.setClientDetailsService(this.clientDetailsService);
            userApprovalHandler.setRequestFactory(oAuth2RequestFactory());
            return userApprovalHandler;
        }

    }


}
