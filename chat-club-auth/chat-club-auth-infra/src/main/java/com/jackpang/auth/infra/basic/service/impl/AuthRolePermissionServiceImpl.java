package com.jackpang.auth.infra.basic.service.impl;

import com.jackpang.auth.infra.basic.entity.AuthRolePermission;
import com.jackpang.auth.infra.basic.mapper.AuthRolePermissionDao;
import com.jackpang.auth.infra.basic.service.AuthRolePermissionService;
import org.springframework.stereotype.Service;
import java.util.List;
import jakarta.annotation.Resource;

/**
 * 角色权限关联表(AuthRolePermission)表服务实现类
 *
 * @author makejava
 * @since 2024-03-24 15:23:28
 */
@Service("authRolePermissionService")
public class AuthRolePermissionServiceImpl implements AuthRolePermissionService {
    @Resource
    private AuthRolePermissionDao authRolePermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthRolePermission queryById(Long id) {
        return this.authRolePermissionDao.queryById(id);
    }
    /**
     * 查询数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public List<AuthRolePermission> queryAllByLimit(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.queryAllByLimit(authRolePermission);
    }

    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public Integer insert(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.insert(authRolePermission);
    }

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    @Override
    public Integer update(AuthRolePermission authRolePermission) {
        return this.authRolePermissionDao.update(authRolePermission);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authRolePermissionDao.deleteById(id) > 0;
    }
}
