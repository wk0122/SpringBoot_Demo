package com.aistar.entity;

import java.util.UUID;

/**
 * Created by js on 2019/6/28.
 */
public class Constant {
    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "nicaicaizheshisha";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
}
