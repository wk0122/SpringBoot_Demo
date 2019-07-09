package com.aistar.controller;

import com.aistar.entity.Ktype;
import com.aistar.entity.Result;
import com.aistar.entity.StatusCode;
import com.aistar.entity.User;
import com.aistar.mapper.KtypeMapper;
import com.aistar.service.IUserService;
import com.aistar.util.TokenUtil;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by js on 2019/6/26.
 */
@RestController
@ResponseBody
public class Hello {
//    @Autowired
//    private KtypeMapper mapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IUserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "我是中国人";
    }

//    @GetMapping("/ktype/{id}")
//    public Ktype find(@PathVariable("id") String id){
//        return mapper.findById(id);
//    }

    @GetMapping("/login")
    public Result login(@RequestBody User user){

        Result result=null;

        Map<String,Object> map=new HashMap();


        User user1=userService.findById(user.getId());



        if (user1==null){
            result=new Result(false, StatusCode.ERROR,"登录失败");
            return result;
        }else {
            String token=tokenUtil.getToken(user1);
            map.put("token",token);
            map.put("user",user1);
            result=new Result(true,StatusCode.OK,"登陆成功",map);
            return result;
        }


    }
}
