package com.plugin.springbootplugintest.controller;

import org.apache.catalina.core.ApplicationContext;
import plugin.PluginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author zlt
 * @version 1.0
 * @date 2021/9/13
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@RestController
public class TestController {
    @Autowired(required = false)
    private PluginInterface pluginInterface;

    @GetMapping("/test")
    public String test() {
        return pluginInterface.sayHello("test plugin");
    }

    @GetMapping("/reload")
    public String reload(@Autowired ApplicationContext applicationContext) {
        ()applicationContext;
        return pluginInterface.sayHello("test plugin");
    }
}
