package club.luozhanghua.oauth2.domain.oauth;

import club.luozhanghua.oauth2.domain.shared.Repository;

import java.util.List;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:45:29
 * @modified By
 */
public interface OauthRepository extends Repository {

    OauthClientDetails findOauthClientDetails(String clientId);

    List<OauthClientDetails> findAllOauthClientDetails();

    void updateOauthClientDetailsArchive(String clientId, boolean archive);

    void saveOauthClientDetails(OauthClientDetails clientDetails);
}