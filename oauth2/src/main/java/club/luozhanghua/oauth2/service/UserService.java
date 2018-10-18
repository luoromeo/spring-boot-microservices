package club.luozhanghua.oauth2.service;

import club.luozhanghua.oauth2.service.dto.UserFormDto;
import club.luozhanghua.oauth2.service.dto.UserJsonDto;
import club.luozhanghua.oauth2.service.dto.UserOverviewDto;

import org.springframework.security.core.userdetails.UserDetailsService;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:57:34
 * @modified By
 */
public interface UserService extends UserDetailsService {

    UserJsonDto loadCurrentUserJsonDto();

    UserOverviewDto loadUserOverviewDto(UserOverviewDto overviewDto);

    boolean isExistedUsername(String username);

    String saveUser(UserFormDto formDto);
}