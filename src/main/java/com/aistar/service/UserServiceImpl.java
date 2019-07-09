package com.aistar.service;

import com.aistar.entity.User;
import com.aistar.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by js on 2019/6/28.
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findById(String id) {

        //该数据是否经常更新
        //是否可丢失
        //不重要数据 直接存储到redis 优先从redis中根据key获取值 没有就从数据库中取
        User user= (User) redisTemplate.opsForValue().get("user_"+id);
        if (null==user){
            user=mapper.findById(id);
            redisTemplate.opsForValue().set("user_"+id,user);
        }
        return user;
    }
}
