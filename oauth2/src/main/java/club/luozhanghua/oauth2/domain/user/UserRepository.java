package club.luozhanghua.oauth2.domain.user;

import club.luozhanghua.oauth2.domain.shared.Repository;

import java.util.List;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:46:54
 * @modified By
 */
public interface UserRepository extends Repository {

    User findByGuid(String guid);

    void saveUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    List<User> findUsersByUsername(String username);
}