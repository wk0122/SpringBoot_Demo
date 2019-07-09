package com.aistar.mapper;

import com.aistar.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * Created by js on 2019/6/28.
 */
public interface UserMapper {

    @Select("select * from user where id=#{id}")
    User findById(String id);
}
