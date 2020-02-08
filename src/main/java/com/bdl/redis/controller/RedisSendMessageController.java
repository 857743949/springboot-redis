package com.bdl.redis.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author bdl
 * @Date 2020/2/7 12:08 下午
 */
@Api(tags = {"RedisSendMessageController"}, description = "redis发布测试")
@RestController
@RequestMapping(value = "/redis")
public class RedisSendMessageController {
    //传string会出现乱码
    @Resource
    private RedisTemplate redisTemplate;
    //解决string乱码问题
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "redis-发布", notes = "redis-发布")
    @GetMapping(value = "/sendMessage")
    public void testPush(@ApiParam(value = "发布的消息内容", required = true) @RequestParam("body") String body){
        /**
         * 使用redisTemplate的convertAndSend()函数，
         * String channel, Object message
         * channel代表管道，
         * message代表发送的信息
         */
        System.out.println("body==="+body);
        stringRedisTemplate.convertAndSend("topic1", body);
    }
}
