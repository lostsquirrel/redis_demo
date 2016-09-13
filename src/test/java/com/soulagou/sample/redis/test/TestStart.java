package com.soulagou.sample.redis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestStart {
	
	@Autowired
	ApplicationContext context;
	@Autowired
	private RedisTemplate<String, String> redisPersistTemplate;
	@Test
	public void testStart() {
		redisPersistTemplate.opsForValue().set("key", "value");
		System.out.println(redisPersistTemplate.opsForValue().get("key"));
	}

}
