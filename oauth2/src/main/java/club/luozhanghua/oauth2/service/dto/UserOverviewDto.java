package club.luozhanghua.oauth2.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:55:24
 * @modified By
 */
public class UserOverviewDto implements Serializable {
    private static final long serialVersionUID = 2023379587030489248L;


    private String username;


    private List<UserDto> userDtos = new ArrayList<>();


    public UserOverviewDto() {
    }

    public int getSize() {
        return userDtos.size();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }
}
