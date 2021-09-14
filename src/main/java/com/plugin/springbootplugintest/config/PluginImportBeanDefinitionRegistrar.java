package com.plugin.springbootplugintest.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 插件类注册bean
 *
 * @author zlt
 * @version 1.0
 * @date 2021/9/14
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
public class PluginImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    /**
     * jar的地址
     */
    private String targetUrl;
    /**
     * 插件类全路径
     */
    private String pluginClass;

    @SneakyThrows
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassLoader classLoader = getClassLoader(targetUrl);
        Class<?> clazz = classLoader.loadClass(pluginClass);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        BeanDefinition beanDefinition = builder.getBeanDefinition();

        registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
    }

    private ClassLoader getClassLoader(String url) {
        try {
            return new URLClassLoader(new URL[] {new URL(url)});
        } catch (Exception e) {
            log.error("getClassLoader-error", e);
            return null;
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.targetUrl = environment.getProperty("targetUrl");
        this.pluginClass = environment.getProperty("pluginClass");
    }
}
