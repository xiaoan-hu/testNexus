package com.bdqn.t0724.bootcachedemo.service;

public interface MyCache {

    public Object get(String key);

    public Object put(String key ,Object value);

    public Object evict(String key);//清理，相当于remove

}
