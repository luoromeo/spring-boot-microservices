package club.luozhanghua.oauth2.infrastructure;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:52:45
 * @modified By
 */
public abstract class PasswordHandler {


    private PasswordHandler() {
    }


    public static String encode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
