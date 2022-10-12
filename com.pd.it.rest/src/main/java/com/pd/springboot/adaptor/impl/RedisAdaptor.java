package com.pd.springboot.adaptor.impl;

import javax.inject.Named;

import com.pd.redis.businessobject.RedisFO;
import com.pd.redis.businessobject.RedisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.pd.springboot.adaptor.IRedisAdaptor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Named
public class RedisAdaptor implements IRedisAdaptor {
	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public String query(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public List<RedisVO> keys(String keys) {
		Set<String> keySet=stringRedisTemplate.keys(keys);
		return keySet.stream().map(key->{
			RedisVO tmpVO=new RedisVO();
			tmpVO.setKey(key);
			return tmpVO;
		}).collect(Collectors.toList());
	}

}
