package com.aistar.controller;

import com.aistar.entity.Result;
import com.aistar.entity.StatusCode;
import com.aistar.util.QiniuUtils;
import com.sun.org.apache.xml.internal.security.keys.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.security.util.KeyUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Created by js on 2019/7/3.
 */
@RestController
@Slf4j
public class UploadController {
    @RequestMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile[] multipartFile, HttpServletRequest request) throws IOException {
        Result result = null;
        // 用来获取其他参数
//        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
//        String name = params.getParameter("username");
        Map<Integer ,String > map=new HashMap<>();
        if (multipartFile.length != 0) {
            for (int i = 0; i < multipartFile.length; i++) {
                FileInputStream inputStream = (FileInputStream) multipartFile[i].getInputStream();
                String path = QiniuUtils.uploadQNImg(inputStream, UUID.randomUUID() + "_" + multipartFile[i].getOriginalFilename());
                System.out.print("七牛云返回的图片链接:" + path);
                map.put(i,path);

            }

            return new Result(true,StatusCode.OK,"上传成功",map);

//        if (!multipartFile.isEmpty()) {
//            FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
//            String path = QiniuUtils.uploadQNImg(inputStream, UUID.randomUUID()+"_"+multipartFile.getOriginalFilename()); // KeyUtil.genUniqueKey()生成图片的随机名
//            System.out.print("七牛云返回的图片链接:" + path);
//            return new Result(true, StatusCode.OK,"上传成功",path);
//        }
//        return new Result(false,StatusCode.ERROR,"上传失败");
        }
        return new Result(false, StatusCode.ERROR, "上传失败");
    }

}
