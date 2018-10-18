package club.luozhanghua.oauth2.service;

import club.luozhanghua.oauth2.domain.oauth.OauthClientDetails;
import club.luozhanghua.oauth2.service.dto.OauthClientDetailsDto;

import java.util.List;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:58:09
 * @modified By
 */
public interface OauthService {

    OauthClientDetails loadOauthClientDetails(String clientId);

    List<OauthClientDetailsDto> loadAllOauthClientDetailsDtos();

    void archiveOauthClientDetails(String clientId);

    OauthClientDetailsDto loadOauthClientDetailsDto(String clientId);

    void registerClientDetails(OauthClientDetailsDto formDto);
}