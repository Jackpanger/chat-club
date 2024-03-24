package com.jackpang.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.jackpang.gateway.redis.RedisUtil;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Resource
    private RedisUtil redisUtil;
    private String authPermissionPrefix = "auth.permission";
    private String authRolePrefix = "auth.role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        return getAuth(loginId, authPermissionPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getAuth(loginId, authRolePrefix);
    }

    private List<String> getAuth(Object loginId, String prefix) {
        String authKey = redisUtil.buildKey(prefix, loginId.toString());
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }
        return new Gson().fromJson(authValue, List.class);
    }

}
