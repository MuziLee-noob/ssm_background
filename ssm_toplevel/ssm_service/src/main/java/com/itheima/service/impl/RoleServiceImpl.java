package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * 查找所有的角色
     * @return
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * 新建角色
     * @param role
     */
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    /**
     * 根据id查找角色
     * @return
     */
    @Override
    public Role findById(String id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public void deleteById(String id) {
        roleDao.deleteById(id);
    }

    /**
     * 根据角色id查询角色权限
     * @param roleId
     * @return
     */
    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    /**
     * 给指定id的角色添加权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
