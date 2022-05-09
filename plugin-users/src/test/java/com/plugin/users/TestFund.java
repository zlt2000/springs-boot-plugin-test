package com.plugin.users;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;

/**
 * TODO
 *
 * @author zlt
 * @version 1.0
 * @date 2022/2/13
 * <p>
 * Blog: https://zlt2000.gitee.io
 * Github: https://github.com/zlt2000
 */
public class TestFund {
    @Test
    public void testDepartmentList1(){
        String code = "002363";
        Integer pageIndex = 1;
        Integer pageSize=20;
        String startTime="2018-1-1";
        String endTime = "2020-4-15";
        String referer = "http://fundf10.eastmoney.com/f10/jjjz_" + code + ".html";
        long time = System.currentTimeMillis();
        String url = "http://api.fund.eastmoney.com/f10/lsjz?callback=jQuery18306596328894644803_1571038362181&" +
                "fundCode=%s&pageIndex=%s&pageSize=%s&startDate=%s&endDate=%s&_=%s";
        url = String.format(url,code,pageIndex,pageSize,startTime,endTime,time);
        System.out.println("url= " + url);
        HttpRequest request = HttpUtil.createGet(url);
        request.header("Referer", referer);
        String str = request.execute().body();
        //获取str的长度
        System.out.println("str=" + str);
        int length = str.length();
        System.out.println("length=" + length);
        //indexOf返回某个指定的字符串值在字符串中首次出现的位置
        int indexStart = str.indexOf("(");
        System.out.println(indexStart);
        //截取字符串
        str = str.substring(indexStart + 9, length - 90);
        System.out.println(str);
        //转换为Obj类型
        JSONObject jsonObject = JSONUtil.parseObj(str);
        System.out.println(jsonObject);
        //获取数组
        JSONArray jsonArray = jsonObject.getJSONArray("LSJZList");
        //计算数组的长度
        int size = jsonArray.size();
        System.out.println(size);
        System.out.println(jsonArray);
    }

    @Test
    public void testDepartmentList2(){
        String code = "002363";
        String referer = "http://so.eastmoney.com/web/s?keyword="+code+"";
        long time = System.currentTimeMillis();

        String url = "http://push3.eastmoney.com/api/qt/stock/get?ut=fa5fd1943c7b386f172d6893dbfba10b&fltt" +
                "=2&fields=f59,f169,f170,f161,f163,f171,f126,f168,f164,f78,f162,f43,f46,f44,f45,f60,f47," +
                "f48,f49,f84,f116,f55,f92,f71,f50,f167,f117,f85,f84,f58,f57,f86,f172,f108,f118,f107,f164," +
                "f177&invt=2&secid=0."+code+"&cb=jQuery1124006112441213993569_1587006450385&_=1587006450403";
        url = String.format(url,code);
        System.out.println("请求url:" + url);
        //http请求
        HttpRequest request = HttpUtil.createGet(url);

        request.header("Referer", referer);
        String str = request.execute().body();
        //获取str的长度
        System.out.println("str=" + str);
        int length = str.length();
        System.out.println("length=" + length);
        //indexOf返回某个指定的字符串值在字符串中首次出现的位置
        int i = str.indexOf("(");
        System.out.println(i);
        //截取字符串
        str = str.substring(i + 55, length - 3);
        System.out.println(str);
        //转换为Obj类型
        JSONObject jsonObject = JSONUtil.parseObj(str);
        System.out.println(jsonObject);
        String fundName = jsonObject.getStr("f58");
        System.out.println(fundName);
    }
}
