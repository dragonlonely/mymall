package com.dragon.xiaomi.commons.redis;
/**
 * @Classname JedisClient
 * @Description TODO
 * @Date 2018/11/29 17:21
 * @Created by Administrator
 */

/**
 * @author Administrator
 * @Date 2018/11/29 17:21
 * @Classname JedisClient
 */
public interface JedisClient {

    String set(String key, String value);//存值

    String get(String key);//取值

    Boolean exists(String key);//判断key是否存在

    Long expire(String key, int seconds);//设置key过期时间

    Long ttl(String key);//返回key的剩余生存时间

    Long incr(String key);//自增1

    Long decr(String key);//自减1

    Long hset(String key, String field, String value);//存hset

    String hget(String key, String field);//取hset

    Long hdel(String key, String... field);//删除hset

    Long del(String key);//删除key

}
