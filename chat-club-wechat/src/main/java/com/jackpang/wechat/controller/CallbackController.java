package com.jackpang.wechat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: CallbackController
 * date: 3/26/24 4:05 PM
 * author: jinhao_pang
 * version: 1.0
 */
@RestController
public class CallbackController {
    @RequestMapping("/test")
    public  String test(){
        return "test";
    }
}
