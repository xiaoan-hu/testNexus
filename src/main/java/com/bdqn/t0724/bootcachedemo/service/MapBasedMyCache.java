package com.bdqn.t0724.bootcachedemo.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//把这个实现变成一个Spring的单例，给全局服务。
//@Component
public class MapBasedMyCache implements MyCache {
    //本实现是线程不安全的。因为HashMap是线程不安全的。
    //考虑到缓存在一个系统中应该只有一个实例，也就是会被多线程访问
    //这个实现是不靠谱的
//    private  final Map<String,Object> map=new HashMap<>();
    //5.0的并发实现是线程安全的，而且性能还不错，推荐使用。
    private  final Map<String,Object> map=new ConcurrentHashMap<>();


    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key,value);
    }

    @Override
    public Object evict(String key) {
        return map.remove(key);
    }
}
