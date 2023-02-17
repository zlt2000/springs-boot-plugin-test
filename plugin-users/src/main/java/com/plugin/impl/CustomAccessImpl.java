package com.plugin.impl;

import com.plugin.AccessData;
import com.plugin.CustomAccessInterface;
import com.plugin.Result;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Date 2023/2/14 18:19
 * @Author qinfei
 **/
@Service
public class CustomAccessImpl implements CustomAccessInterface {
    @Override
    public Result customAccess(AccessData accessData) {
        Result result = new Result();
        result.message="测试接口调用";
        return result;
    }
}
