package com.plugin.users.controller;

import com.plugin.PluginInterface;
import com.plugin.users.utils.ClassLoaderUtil;
import com.plugin.users.utils.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TODO
 *
 * @author zlt
 * @version 1.0
 * @date 2021/10/5
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
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
        Object bean = springUtil.getBean(clazz.getName());
        if(bean instanceof PluginInterface){
            PluginInterface plugin = (PluginInterface)bean;
            this.pluginInterface = plugin;
            return plugin.sayHello("test reload");
        }else{
            log.info(bean.getClass().getInterfaces()[0].getName());
            return bean.toString();
        }
    }

    /**
     * 移除bean
     * @return
     * @throws ClassNotFoundException
     */
    @GetMapping("/remove")
    public Object remove() throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader(targetUrl);
        Class<?> clazz = classLoader.loadClass(pluginClass);
        springUtil.removeBean(clazz.getName());
        this.pluginInterface = null;
        Object bean = springUtil.getBean(clazz.getName());
        if(bean!=null) {
            log.info(bean.toString());
        }
        return clazz.getName();
    }
}
