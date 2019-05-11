package com.vcyber.user.util;

import com.github.jedis.lock.JedisLock;
import redis.clients.jedis.Jedis;

public class JedisLockTest {

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<10;i++){
            int num = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis=new Jedis("127.0.0.1",6379);
                    lock(jedis,"key", num);
                }
            }).start();
        }

    }

    public static void lock(Jedis jedis, String key,int i){
        JedisLock jedisLock=new JedisLock(jedis,key,2000,2000);
        try {
            //获得锁
            jedisLock.acquire();
            System.out.println(i+"获得锁");
            if(i==2){
                //该代码块是为了验证效果
                System.out.println(i+"睡眠2秒....");
                Thread.sleep(2000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(i+"释放锁");
            if(jedisLock!=null){
                //释放锁
                jedisLock.release();
            }
            if(jedis!=null){
                jedis.close();
            }
        }
    }

}

