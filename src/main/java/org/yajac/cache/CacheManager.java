package org.yajac.cache;

import redis.clients.jedis.Jedis;

import java.util.List;

public class CacheManager {

	private static final String redisServer = System.getenv("redis.server");

	public static List<String> getCacheValues(final String hash) {
		final Jedis jedis = new Jedis(redisServer);
		final List<String> value = jedis.hvals(hash);
		jedis.close();
		return value;
	}

	public static void setCacheValues(final String hash, final String key, final String value) {
		final Jedis jedis = new Jedis(redisServer);
		jedis.hsetnx(hash, key, value);
		jedis.close();
	}

}
