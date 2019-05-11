package com.vcyber.user.util;

import org.springframework.data.redis.core.RedisTemplate;

public class TestRedisLock {

    private static RedisTemplate redisTemplate;


    public static void main(String[] args) {
        String key = "key_gwh";
        RedisLock lock = new RedisLock(redisTemplate, key, 10000, 20000);
        try {
            if (lock.lock()) {
                //需要加锁的代码
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }finally {
            //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
            //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
            lock.unlock();
        }
   }

}
