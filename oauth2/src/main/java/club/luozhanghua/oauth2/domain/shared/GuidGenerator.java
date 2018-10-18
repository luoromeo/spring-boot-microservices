package club.luozhanghua.oauth2.domain.shared;

import java.util.UUID;

import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:46:40
 * @modified By
 */
public abstract class GuidGenerator {


    private static RandomValueStringGenerator defaultClientSecretGenerator = new RandomValueStringGenerator(32);


    /**
     * private constructor
     */
    private GuidGenerator() {
    }

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateClientSecret() {
        return defaultClientSecretGenerator.generate();
    }

}