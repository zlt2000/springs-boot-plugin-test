package com.plugin.impl;

import com.plugin.AccessData;
import com.plugin.CustomAccessInterface;
import com.plugin.PluginInterface;
import com.plugin.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PluginImpl implements PluginInterface {

    @Autowired
    private CustomAccessInterface customAccessInterface;

    @Override
    public String sayHello(String name) {
        Result result = customAccessInterface.customAccess(new AccessData());
        return result.message;
    }
}
