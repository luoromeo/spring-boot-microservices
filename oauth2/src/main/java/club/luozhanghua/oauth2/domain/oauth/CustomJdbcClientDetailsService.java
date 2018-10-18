package club.luozhanghua.oauth2.domain.oauth;

import javax.sql.DataSource;

import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:44:18
 * @modified By
 */
public class CustomJdbcClientDetailsService extends JdbcClientDetailsService {

    private static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            "web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            "from oauth_client_details where client_id = ? and archived = 0 ";


    public CustomJdbcClientDetailsService(DataSource dataSource) {
        super(dataSource);
        setSelectClientDetailsSql(SELECT_CLIENT_DETAILS_SQL);
    }


}