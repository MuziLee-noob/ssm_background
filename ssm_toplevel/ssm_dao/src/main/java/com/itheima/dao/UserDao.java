package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {

    /**
     * 通过用户名查找用户信息，登录使用
     * @param username 用户名
     * @return 用户对象
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findByUsername(String username);

    /**
     * 查找所有用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 保存用户
     * @param userinfo
     */
    @Insert("insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userinfo);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.RoleDao.findRoleByUserId")),
    })
    UserInfo findById(String id);

    @Select("select * from users where id in (select userId from users_role where roleId = #{roleId})")
    List<UserInfo> findByRoleId(String roleId);

    /**
     * 根据用户id查找用户角色
     * @param id
     * @return
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findOtherRoles(String id);

    /**
     * 给指定id的用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role (userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
