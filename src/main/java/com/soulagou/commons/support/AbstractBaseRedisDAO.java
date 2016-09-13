package com.soulagou.commons.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.soulagou.commons.utils.RedisKeyGenerator;

public abstract class AbstractBaseRedisDAO<T, ID extends Serializable>
		implements BaseRedisDAO<T, ID> {

	@Override
	public T find(ID id) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		return this.getValueOptions().get(key);
	}

	@Override
	public void save(ID id, T object) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		this.getValueOptions().set(key, object);
	}

	@Override
	public void delete(ID id) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		this.getValueOptions().getOperations().delete(key);
	}

	@Override
	public List<T> getList(Collection<ID> arg0) {
		List<String> keys = new ArrayList<String>();
		for (ID id : arg0) {
			String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
			keys.add(key);
		}
		return this.getValueOptions().multiGet(keys);
	}
	
	@Override
	public void push(ID id, T object) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		ListOperations<String, T> ops = this.getListOptions();
		ops.leftPush(key, object);
		Long size = ops.size(key);
		if (size > this.getQueueSize()) {
			ops.rightPop(key);
		}
	}
	
	@Override
	public List<T> range(ID id, int firstResult, int maxResults) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		ListOperations<String, T> ops = this.getListOptions();
		return ops.range(key, firstResult, firstResult + maxResults -1);
	}
	
	@Override
	public long count(ID id) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		ListOperations<String, T> ops = this.getListOptions();
		return ops.size(key);
	}
	
	@Override
	public boolean hasKey(ID id) {
		String key = RedisKeyGenerator.generate(this.getPrefix(), id.toString());
		return this.getRedisTemplate().hasKey(key);
	}
	
	protected ValueOperations<String, T> getValueOptions() {
		return this.getRedisTemplate().opsForValue();
	}
	
	protected ListOperations<String, T> getListOptions() {
		return this.getRedisTemplate().opsForList();
	}
	
	protected int getQueueSize() {
		return 500;
	}
	
	protected abstract RedisTemplate<String, T> getRedisTemplate();
	
	protected abstract String getPrefix();

}
