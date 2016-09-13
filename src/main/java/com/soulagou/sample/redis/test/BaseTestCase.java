package com.soulagou.sample.redis.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.soulagou.sample.redis.domain.DemoBean;

public class BaseTestCase {

	public static void main(String[] args) {
		ApplicationContext context  = new ClassPathXmlApplicationContext("application-context.xml");
		DemoBean db = (DemoBean) context.getBean("demoBean");
		System.out.println(db);
	}
}
