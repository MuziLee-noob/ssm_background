package com.itheima.dao;

import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    /**
     * 根据用户的id查询角色
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId")),
    })
    List<Role> findRoleByUserId(String userId);

    /**
     * 查找所有的角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 新建角色
     * @param role
     */
    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    void save(Role role);

    /**
     * 根据角色id查询角色详细信息
     * @param id
     * @return
     */
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.PermissionDao.findByRoleId")),
            @Result(property = "users", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.dao.UserDao.findByRoleId")),
    })
    Role findRoleById(String id);

    /**
     * 删除角色
     * @param id
     */
    @Delete("delete from role where id = #{id}")
    void deleteById(String id);
}
