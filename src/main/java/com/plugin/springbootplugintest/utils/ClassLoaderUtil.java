package com.plugin.springbootplugintest.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 类加载器工具类
 *
 * @author zlt
 * @version 1.0
 * @date 2021/9/14
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
@Slf4j
public class ClassLoaderUtil {
    public static ClassLoader getClassLoader(String url) {
        try {
            return new URLClassLoader(new URL[] {new URL(url)});
        } catch (Exception e) {
            log.error("getClassLoader-error", e);
            return null;
        }
    }
}
