package com.bdqn.t0724.bootcachedemo.service;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisBasedMyCache implements MyCache {

    private Jedis jcache=new Jedis("127.0.0.1");

    public RedisBasedMyCache() {
        //如果需要更多的参数配置，用构造方法来实现
        //或者，把Jedis也做成一个Spring的组件，注入进来
    }

    //如果不使用泛型，fastjson应该没有能力构建一个自定义的对象
    @Override
    public Object get(String key) {
        String jsonText = jcache.get(key);
        return JSON.parse(jsonText);
    }

    @Override
    public Object put(String key, Object value) {
        return jcache.set(key,JSON.toJSONString(value));
    }

    @Override
    public Object evict(String key) {
        return jcache.del(key);
    }
}
