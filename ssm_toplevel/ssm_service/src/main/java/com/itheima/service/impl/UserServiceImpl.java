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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 登录验证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        //此处user对象为spring security中的User对象
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() != 0,true,true,true, getAuthority(userInfo));
        return user;
    }

    //给登录方法提供用户角色list
    private List<SimpleGrantedAuthority> getAuthority(UserInfo userInfo) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : userInfo.getRoles()) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    /**
     * 查找所有用户
     * @return
     */
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    /**
     * 添加用户
     * @param userinfo
     */
    @Override
    public void save(UserInfo userinfo) {
        //对密码进行加密
        userinfo.setPassword(bCryptPasswordEncoder.encode(userinfo.getPassword()));
        userDao.save(userinfo);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }
}
