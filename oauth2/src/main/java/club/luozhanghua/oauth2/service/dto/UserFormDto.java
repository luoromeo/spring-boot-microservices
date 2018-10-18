package club.luozhanghua.oauth2.service.dto;


import club.luozhanghua.oauth2.domain.user.Privilege;
import club.luozhanghua.oauth2.domain.user.User;
import club.luozhanghua.oauth2.infrastructure.PasswordHandler;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:55:08
 * @modified By
 */
public class UserFormDto extends UserDto {
    private static final long serialVersionUID = 7959857016962260738L;


    private String password;

    public UserFormDto() {
    }


    public Privilege[] getAllPrivileges() {
        return new Privilege[]{Privilege.MOBILE, Privilege.UNITY};
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User newUser() {
        final User user = new User()
                .username(getUsername())
                .phone(getPhone())
                .email(getEmail())
                .password(PasswordHandler.encode(getPassword()));
        user.privileges().addAll(getPrivileges());
        return user;
    }
}
