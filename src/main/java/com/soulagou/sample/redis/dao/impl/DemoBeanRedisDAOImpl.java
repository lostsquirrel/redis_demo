package com.soulagou.sample.redis.dao.impl;

import javax.inject.Inject;

import org.springframework.data.redis.core.RedisTemplate;

import com.soulagou.commons.support.AbstractBaseRedisDAO;
import com.soulagou.commons.support.BaseRedisDAO;
import com.soulagou.sample.redis.domain.DemoBean;

public class DemoBeanRedisDAOImpl extends AbstractBaseRedisDAO<DemoBean, String> implements
		BaseRedisDAO<DemoBean, String> {

	private final static String PREFIX = "DEMO_BEAN";
	@Inject
	private RedisTemplate<String, DemoBean> redisPersistTemplate;
	
	@Override
	protected RedisTemplate<String, DemoBean> getRedisTemplate() {
		return this.redisPersistTemplate;
	}

	@Override
	protected String getPrefix() {
		return PREFIX;
	}

}
