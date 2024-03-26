package com.jackpang.auth.infra.basic.service;

import com.jackpang.auth.infra.basic.entity.AuthPermission;

import java.util.List;

/**
 * (AuthPermission)表服务接口
 *
 * @author makejava
 * @since 2024-03-24 14:49:51
 */
public interface AuthPermissionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthPermission queryById(Long id);
    
    /**
     * 查询数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    List<AuthPermission> queryAllByLimit(AuthPermission authPermission);
    
    /**
     * 新增数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    Integer insert(AuthPermission authPermission);

    /**
     * 修改数据
     *
     * @param authPermission 实例对象
     * @return 实例对象
     */
    Integer update(AuthPermission authPermission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);


    List<AuthPermission> queryByPermissionList(List<Long> permessionIdList);
}
