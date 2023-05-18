package com.ilovesshan.im.core.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/11/30
 * @description:
 */
public class UserCache {

    private static final ThreadLocal<Map<String, String>> THREAD_LOCAL = new ThreadLocal<>();

    //判断线程map是否为空，为空就添加一个map
    public static Map<String, String> getLocalMap() {
        Map<String, String> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new HashMap<>(16);
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    //把用户信息添加到线程map中
    public static void set(String key, String name) {
        Map<String, String> map = getLocalMap();
        map.put(key, name);
    }

    //获得线程map中的数据
    public static String get(String key) {
        Map<String, String> map = getLocalMap();
        return map.get(key);
    }
}