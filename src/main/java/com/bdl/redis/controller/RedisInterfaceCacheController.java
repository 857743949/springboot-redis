package com.bdl.redis.controller;

import com.bdl.redis.service.RedisMethodsCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Redis 接口和方法缓存数据
 * @Author bdl
 * @Date 2020/2/7 12:09 下午
 */
@RestController
@RequestMapping(value = "redis/cache")
@Api(tags = {"RedisInterfaceCacheController"}, description = "redis 接口缓存")
public class RedisInterfaceCacheController {

    @Resource
    private RedisMethodsCacheService redisMethodsCacheService;

    @GetMapping(value = "/saveRedis")
    @ApiOperation(value = "新增接口", notes = "新增接口：每次新增数据，要清空缓存组，保证缓存与数据库同步")
    public Object savaCacheRedis(@ApiParam(value = "传入key值") @RequestParam String key) {
        return redisMethodsCacheService.saveData(key);
    }

    @GetMapping(value = "/getRedis")
    @ApiOperation(value = "查询接口", notes = "查询接口：首次查询缓存中没有，再到数据库查询，将查询返回的结果保存到缓存中，下次请求在缓存中取数据")
    public Object getCacheRedis(@ApiParam(value = "传入key值") @RequestParam String key) {
        return redisMethodsCacheService.getData(key);
    }

    @GetMapping(value = "/putRedis")
    @ApiOperation(value = "更新接口", notes = "更新接口：同时更新缓存库")
    public Object putCacheRedis(@ApiParam(value = "传入key值") @RequestParam String key) {
        return redisMethodsCacheService.putData(key);
    }

    @GetMapping(value = "/deleteRedis")
    @ApiOperation(value = "删除数据", notes = "删除数据：同时删除缓存库中对应组的某个缓存 ，目的保证数据一致性")
    public Object deleteCacheRedis(@ApiParam(value = "传入key值") @RequestParam String key) {
        return redisMethodsCacheService.deleteData(key);
    }

    @GetMapping(value = "/deleteAllRedis")
    @ApiOperation(value = "删除所有数据", notes = "删除所有数据：同时删除缓存库中对应组下面所有的缓存 ，目的保证数据一致性")
    public Object deleteAllCacheRedis(@ApiParam(value = "传入key值") @RequestParam String key) {
        return redisMethodsCacheService.deleteAllData(key);
    }

}
