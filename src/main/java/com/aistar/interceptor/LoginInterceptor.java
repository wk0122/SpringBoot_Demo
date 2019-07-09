package com.aistar.interceptor;

import com.aistar.entity.Result;
import com.aistar.entity.StatusCode;
import com.aistar.entity.User;
import com.aistar.service.IUserService;
import com.aistar.util.TokenUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by js on 2019/6/27.
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");

        ServletOutputStream out=response.getOutputStream();//不能用getWriter()
        //获得头请求中的token
        String token=request.getHeader("Authorization").substring(7);

        Result result=null;

        System.out.println(token);
        if(token==null){
            result=new Result(false, StatusCode.ERROR,"无token,请重新登录");
            String userJsonStr= JSON.toJSONString(result);
            out.println(userJsonStr);

            return false;
        }

        //获取token中的id
        String userId=tokenUtil.paraseToken(token).getId();

        //根据id拿到对象
        User user=iUserService.findById(userId);
        System.out.println(userId);

        if (null==user){
            result=new Result(false, StatusCode.ERROR,"登录失败");
            String userJsonStr= JSON.toJSONString(result);
            out.println(userJsonStr);
            return false;
        }

        System.out.println("======");
        return true;
    }
}
