package com.plugin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan("com.plugin.impl")
@SpringBootApplication
//@Import(PluginImportBeanDefinitionRegistrar.class)
public class SpringBootPluginTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootPluginTestApplication.class, args);
    }
}
