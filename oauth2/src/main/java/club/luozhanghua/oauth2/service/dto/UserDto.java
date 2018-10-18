/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package club.luozhanghua.oauth2.service.dto;

import club.luozhanghua.oauth2.domain.user.Privilege;
import club.luozhanghua.oauth2.domain.user.User;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * @description
 * @author zhanghua.luo
 * @date 2018年10月17日 04:53:50
 * @modified By
 */
public class UserDto implements Serializable {
    private static final long serialVersionUID = -2502329463915439215L;


    private String guid;

    private String username;

    private String phone;
    private String email;

    private String createTime;

    private List<Privilege> privileges = new ArrayList<>();


    public UserDto() {
    }


    public UserDto(User user) {
        this.guid = user.guid();
        this.username = user.username();
        this.phone = user.phone();
        this.email = user.email();

        this.privileges = user.privileges();
        this.createTime = user.createTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public static List<UserDto> toDtos(List<User> users) {
        List<UserDto> dtos = new ArrayList<>(users.size());
        for (User user : users) {
            dtos.add(new UserDto(user));
        }
        return dtos;
    }
}
