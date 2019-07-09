package com.aistar.mapper;

import com.aistar.entity.Ktype;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/6/20 0020
 */
@Mapper
public interface KtypeMapper {

    @Select("select * from ktype")
    List<Ktype> findAll();

//    @Select("select * from ktype where type like concat('%',#{type},'%')")
//    List<Ktype> findAll(String type);

    Ktype findById(String id);
}
