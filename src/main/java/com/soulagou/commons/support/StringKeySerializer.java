package com.soulagou.commons.support;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class StringKeySerializer implements RedisSerializer<String> {

	@Override
	public byte[] serialize(String key) throws SerializationException {
		return key.getBytes();
	}

	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
		return new String(bytes);
	}

}
