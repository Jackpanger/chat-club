package com.jackpang.auth.infra.basic.service.impl;

import com.jackpang.auth.infra.basic.entity.AuthPermission;
import com.jackpang.auth.infra.basic.dao.AuthPermissionDao;
import com.jackpang.auth.infra.basic.service.AuthPermissionService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

import java.util.List;

/**
 * (AuthPermission)表服务实现类
 *
 * @author makejava
 * @since 2024-03-24 14:49:51
 */
@Service("authPermissionService")
public class AuthPermissionServiceImpl implements AuthPermissionService {
    @Resource
    private AuthPermissionDao authPermissionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthPermission queryById(Long id) {
        return this.authPermissionDao.queryById(id);
    }
    /**
     * 查询数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public List<AuthPermission> queryAllByLimit(AuthPermission authPermission) {
        return this.authPermissionDao.queryAllByLimit(authPermission);
    }

    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public AuthPermission insert(AuthPermission authPermission) {
        this.authPermissionDao.insert(authPermission);
        return authPermission;
    }

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    @Override
    public AuthPermission update(AuthPermission authPermission) {
        this.authPermissionDao.update(authPermission);
        return this.queryById(authPermission.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.authPermissionDao.deleteById(id) > 0;
    }
}
