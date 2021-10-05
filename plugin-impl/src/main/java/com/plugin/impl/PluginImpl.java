package com.plugin.impl;

import com.plugin.PluginInterface;

public class PluginImpl implements PluginInterface {
    @Override
    public String sayHello(String name) {
        String result = "Hello " + name;
        System.out.println(result);
        return result;
    }
}
