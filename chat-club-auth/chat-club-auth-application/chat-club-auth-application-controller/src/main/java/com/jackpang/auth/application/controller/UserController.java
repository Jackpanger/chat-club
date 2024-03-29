package com.jackpang.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jackpang.auth.application.convert.AuthUserDTOConverter;
import com.jackpang.auth.application.dto.AuthUserDTO;
import com.jackpang.auth.common.entity.Result;
import com.jackpang.auth.domain.entity.AuthUserBO;
import com.jackpang.auth.domain.service.AuthUserDomainService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
@Slf4j
public class UserController {
    @Resource
    private AuthUserDomainService authUserDomainService;

    @RequestMapping("register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register authUserDTOh:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDOtoToBO(authUserDTO);

            return Result.ok(authUserDomainService.register(authUserBO));
        } catch (Exception e) {
            log.error("UserController.register error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.update authUserDTOh:{}", JSON.toJSONString(authUserDTO));
            }
            checkUserInfo(authUserDTO);
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDOtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.update error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.getUserInfo authUserDTOh:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "Username is null");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDOtoToBO(authUserDTO);
            AuthUserBO userInfo = authUserDomainService.getUserInfo(authUserBO);
            return Result.ok(AuthUserDTOConverter.INSTANCE.convertBOtoToDO(userInfo));
        } catch (Exception e) {
            log.error("UserController.getUserInfo error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("logout")
    public Result logout(@RequestParam("userName") String userName) {
        try {
            log.info("UserController.logout userName:{}", userName);

            Preconditions.checkArgument(!StringUtils.isBlank(userName), "Username is null");
            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("UserController.logout error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("changeStatus")
    public Result<Boolean> changeStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.changeStatus authUserDTOh:{}", JSON.toJSONString(authUserDTO));
            }
            Preconditions.checkNotNull(authUserDTO.getId(), "ID is null");
            Preconditions.checkNotNull(authUserDTO.getStatus(), "status is null");
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDOtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.update(authUserBO));
        } catch (Exception e) {
            log.error("UserController.changeStatus error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @RequestMapping("delete")
    public Result<Boolean> delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.delete authUserDTOh:{}", JSON.toJSONString(authUserDTO));
            }
            AuthUserBO authUserBO = AuthUserDTOConverter.INSTANCE.convertDOtoToBO(authUserDTO);
            return Result.ok(authUserDomainService.delete(authUserBO));
        } catch (Exception e) {
            log.error("UserController.delete error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    private void checkUserInfo(@RequestBody AuthUserDTO authUserDTO) {
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()), "Username is null");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()), "Email is null");
        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()), "Password is null");

    }

    @RequestMapping("doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("validCode") String validCode) {
        try {
            Preconditions.checkArgument(!StringUtils.isBlank(validCode), "validCode is null");
            return Result.ok(authUserDomainService.doLogin(validCode));
        } catch (Exception e) {
            log.error("UserController.doLogin error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }
}
