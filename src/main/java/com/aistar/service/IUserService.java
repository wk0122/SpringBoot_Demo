package com.aistar.service;

import com.aistar.entity.User;

/**
 * Created by js on 2019/6/28.
 */
public interface IUserService {

    User findById(String id);
}
