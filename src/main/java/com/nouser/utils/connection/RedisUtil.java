package com.nouser.utils.connection;

import java.util.Set;

import com.nouser.utils.file.PropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public final class RedisUtil {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
	
	private static final String ADDR = PropertiesUtil.getProperty("redis.hostName"); // ip
	private static final int PORT = Integer.valueOf(PropertiesUtil.getProperty("redis.port")); // 端口
	private static final String AUTH = PropertiesUtil.getProperty("redis.pwd"); // 密码(原始默认是没有密码)
	
	private static int MAX_ACTIVE = 1500; // 最大连接数
	private static int MAX_IDLE = 200; // 设置最大空闲数
	private static int MAX_WAIT = 3000; // 最大连接时间
	private static int TIMEOUT = 10000; // 超时时间
	private static boolean BORROW = true; // 在borrow一个事例时是否提前进行validate操作
	private static JedisPool jedisPool = null;
	public static boolean jedis_status = true;

	/**
	* 初始化Redis连接池
	*/
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(BORROW);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		} catch (Exception e) {
			logger.error("redis连接超时~~",e);
			senMail();
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			logger.error("redis连接超时~~",e);
			senMail();
		}
		return jedis;
	}
	
	public static void releaseResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

	/**
	 * 设置 String
	 * @param key
	 * @param value
	 */
	public static int setString(String key, String value) {
		Jedis jedis = null;
		try {
			value = StringUtils.isEmpty(value) ? "" : value;
			jedis = getJedis();
			jedis.set(key, value);
			return 0;
		} catch (Exception e) {
			logger.error("redis连接超时~~",e);
			senMail();
		} finally {
			releaseResource(jedis);
		}
		return -1;
	}

	/**
	 * 设置 过期时间
	 * @param key
	 * @param seconds 以秒为单位
	 * @param value
	 */
	public static void setString(String key, int seconds, String value) {
		Jedis jedis = null;
		try {
			value = StringUtils.isEmpty(value) ? "" : value;
			jedis = getJedis();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("redis连接超时~~",e);
			senMail();
		} finally {
			releaseResource(jedis);
		}
	}

	/**
	 * 设置 String
	 * @param key
	 * @param value
	 */
	public static long delKey(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.del(key);
		} catch (Exception e) {
			logger.error("redis连接超时~~",e);
			senMail();
		} finally {
			releaseResource(jedis);
		}
		return -1;
	}
	
	/**
	 * 根据key 获取redis对应的值
	 * @param key
	 * @return
	 */
	public static String getStr(String key) {
			Jedis jedis = null;
			try {
				jedis = getJedis();
				if (jedis == null) {
					return null;
				}
				if (!jedis.exists(key)) {
					return null;
				} else {
					String number = jedis.get(key);
					return number;
				}
			} catch (Exception e1) {
				logger.error("redis连接超时~~",e1);
				senMail();
			} finally {
				releaseResource(jedis);
			}
		return null;
	}
	
	/**
	 * 设置 自增长
	 */
	public static Long getIncr(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.incr(key);
		} catch (Exception e) {
			logger.error("redis连接超时~~", e);
		} finally {
			releaseResource(jedis);
		}
		return null;
	}
	
//	=============================  set 集合 =================================================
	/**
	 * 向集合添加一个成员
	 * @param key
	 * @param value	
	 * @return	0:重复插入;1:插入成功;-1:异常
	 */
	public static int sadd(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.sadd(key, value).intValue();
		} catch (Exception e) {
			logger.error("redis连接超时~~", e);
		} finally {
			releaseResource(jedis);
		}
		return -1;
	}
	
	/**
	 * 集合的成员数
	 * @param key
	 * @return	-1:异常
	 */
	public static int scard(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.scard(key).intValue();
		} catch (Exception e) {
			logger.error("redis连接超时~~", e);
		} finally {
			releaseResource(jedis);
		}
		return -1;
	}

	/**
	 * 获取集合中所有元素
	 * @param key
	 * @return
	 */
	public static Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.smembers(key);
		} catch (Exception e) {
			logger.error("redis连接超时~~", e);
		} finally {
			releaseResource(jedis);
		}
		return null;
	}

	/**
	 * 随机弹出元素
	 * @param key
	 * @param num
	 * @return
	 */
	public static String spop(String key) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.spop(key);
		} catch (Exception e) {
			logger.error("redis连接超时~~", e);
		} finally {
			releaseResource(jedis);
		}
		return null;
	}
	
	/**
	 * 随机弹出num个元素
	 * TODO:	老版本redis不支持
	 * @param key
	 * @param num
	 * @return
	 */
	public static Set<String> spop(String key, long num) {
		Jedis jedis = null;
		try {
			jedis = getJedis();
			return jedis.spop(key, num);
		} catch (Exception e) {
			logger.error("redis连接超时~~", e);
		} finally {
			releaseResource(jedis);
		}
		return null;
	}
	
	public static void senMail() {
		jedis_status = false;
		try {
//			String ip = InetAddress.getLocalHost().getHostAddress();
//			String emailText = ip + "服务器连接redis服务器失败，请及时查收~【红牛】";
//			SendMail.sendEmail(null, emailText);
		} catch (Exception e) {
			logger.error("redis连接超时~~",e);
		}
	}
}