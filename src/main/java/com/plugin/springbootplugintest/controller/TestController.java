package com.plugin.springbootplugintest.controller;

import com.plugin.springbootplugintest.utils.ClassLoaderUtil;
import com.plugin.springbootplugintest.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Value;
import plugin.PluginInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private SpringUtil springUtil;

    /**
     * jar的地址
     */
    @Value("${targetUrl}")
    private String targetUrl;
    /**
     * 插件类全路径
     */
    @Value("${pluginClass}")
    private String pluginClass;

    @GetMapping("/test")
    public String test() {
        return pluginInterface.sayHello("test plugin");
    }

    /**
     * 运行时注册bean
     */
    @GetMapping("/reload")
    public Object reload() throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader(targetUrl);
        Class<?> clazz = classLoader.loadClass(pluginClass);
        springUtil.registerBean(clazz.getName(), clazz);
        PluginInterface plugin = (PluginInterface)springUtil.getBean(clazz.getName());
        return plugin.sayHello("test reload");
    }
}
