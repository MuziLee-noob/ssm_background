package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), userInfo.getStatus() != 0,true,true,true, getAuthority(userInfo));
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(UserInfo userInfo) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : userInfo.getRoles()) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }
}
