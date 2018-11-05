package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserInfo> findAll();

    void save(UserInfo userinfo);

    UserInfo findById(String id);

    List<Role> findOtherRoles(String id);

    void addRoleToUser(String userId, String[] roleIds);
}
