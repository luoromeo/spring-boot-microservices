package club.luozhanghua.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月19日 22:35
 * @modified By
 */
@Configuration
public class TokenStoreConfiguration {

    /**
     * redis连接工厂
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 用于存放token
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "club.luoromeo.security.oauth2", name = "storeType", havingValue = "redis")
    public TokenStore redisTokenStore() {
        return new MyRedisTokenStore(redisConnectionFactory);
    }

    /**
     * jwt TOKEN配置信息
     */
    @Configuration
    @ConditionalOnProperty(prefix = "club.luoromeo.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
    public static class JwtTokenConfig {

        /**
         * 使用jwtTokenStore存储token
         * @return
         */
        @Bean
        public JwtTokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * 用于生成jwt
         * @return
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
            //生成签名的key
            accessTokenConverter.setSigningKey("merryyou");
            return accessTokenConverter;
        }

        /**
         * 用于扩展JWT
         * @return
         */
        @Bean
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            return new MyJwtTokenEnhancer();
        }
    }
}
