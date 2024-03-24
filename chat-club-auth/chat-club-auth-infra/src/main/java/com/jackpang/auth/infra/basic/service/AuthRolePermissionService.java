package com.jackpang.auth.infra.basic.service;

import com.jackpang.auth.infra.basic.entity.AuthRolePermission;
import java.util.List;

/**
 * 角色权限关联表(AuthRolePermission)表服务接口
 *
 * @author makejava
 * @since 2024-03-24 15:23:28
 */
public interface AuthRolePermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthRolePermission queryById(Long id);
    
    /**
     * 查询数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    List<AuthRolePermission> queryAllByLimit(AuthRolePermission authRolePermission);
    
    /**
     * 新增数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    Integer insert(AuthRolePermission authRolePermission);

    /**
     * 修改数据
     *
     * @param authRolePermission 实例对象
     * @return 实例对象
     */
    Integer update(AuthRolePermission authRolePermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
