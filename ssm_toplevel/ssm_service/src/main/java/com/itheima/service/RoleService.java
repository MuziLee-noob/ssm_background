package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void save(Role role);

    Role findById(String id);

    void deleteById(String id);

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
