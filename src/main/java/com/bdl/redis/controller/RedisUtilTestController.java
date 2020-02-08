package com.bdl.redis.controller;

import com.bdl.redis.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Author bdl
 * @Date 2020/2/7 12:08 下午
 */
@RestController
@RequestMapping(value = "redis/util")
@Api(tags = {"RedisUtilTestController"}, description = "redisUtil 工具测试")
public class RedisUtilTestController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping(value = "/redisSave")
    @ApiOperation(value = "保存到redis,并返回结果",notes = "保存到redis")
    public Object redisSave(@ApiParam(value = "传入key值") @RequestParam String key,
                        @ApiParam(value = "传入value值") @RequestParam String value){
        redisUtil.set(key,value);
        return redisUtil.get(key);
    }

    @GetMapping(value = "/redisGetAllKeyValue")
    @ApiOperation(value = "获取redis种所有key和value值",notes = "获取redis种所有key和value值")
    public Object redisGetAllKeyValue(){
        Set<String> keys = redisUtil.keys("*");
        Iterator<String> iterator = keys.iterator();
        Map<String,Object> map = new HashMap<>();
        while (iterator.hasNext()){
            String key = iterator.next();
            Object o = redisUtil.get(key);
            map.put(key,o);
        }
        return map;
    }


    @PostMapping(value = "/deleteRedisAll")
    @ApiOperation(value = "清除redis所有缓存",notes = "清除redis所有缓存")
    public Object deleteRedisAll(){
        Set<String> keys = redisUtil.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            redisUtil.del(iterator.next());
        }
        return "删除redis所有数据成功";
    }

    @GetMapping(value = "/getRedisValue")
    @ApiOperation(value = "根据key获取value值",notes = "根据key获取value值")
    public Object getRedisValue(@ApiParam(value = "传入key值") @RequestParam String key){
        return redisUtil.get(key);
    }

}
