package com.plugin.springbootplugintest.config;

import com.plugin.springbootplugintest.utils.ClassLoaderUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 启动时注册bean
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
        ClassLoader classLoader = ClassLoaderUtil.getClassLoader(targetUrl);
        Class<?> clazz = classLoader.loadClass(pluginClass);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
        BeanDefinition beanDefinition = builder.getBeanDefinition();

        registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.targetUrl = environment.getProperty("targetUrl");
        this.pluginClass = environment.getProperty("pluginClass");
    }
}
