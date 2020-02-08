package com.bdl.redis.service;

/**
 * redis 缓存接口的方法返回的数据测试
 * @Author bdl
 * @Date 2020/2/7 12:08 下午
 */
public interface RedisMethodsCacheService {
    String getData();

    String saveData(String parameter);
    String getData(String parameter);
    String putData(String parameter);
    String deleteData(String parameter);
    String deleteAllData(String parameter);
}
