package com.plugin.springbootplugintest;

import com.plugin.springbootplugintest.config.PluginImportBeanDefinitionRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(PluginImportBeanDefinitionRegistrar.class)
public class SpringBootPluginTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPluginTestApplication.class, args);
    }
}
