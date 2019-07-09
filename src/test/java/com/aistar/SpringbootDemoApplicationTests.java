package com.aistar;

import com.aistar.mapper.KtypeMapper;
import com.aistar.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootDemoApplicationTests {
	@Autowired
	private IUserService mapper;


	@Test
	public void contextLoads() {


		log.info("查询id为1的用户");
		System.out.println(mapper.findById("1"));
	}

}
