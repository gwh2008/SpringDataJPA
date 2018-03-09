package com.vcyber.user.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ========================
 * Created: gaowh
 * 描述:使用@Scheduled创建定时任务
 * Date：2018/2/23
 * Time：15:12
 * ========================
 */

@Component
public class PrintTask {


    /**
     * 每小时的10分执行该方法
     */
    @Scheduled(cron = "0 10 * * * *")
    public void cron() throws Exception
    {
        System.out.println("执行测试cron时间："+ new Date(System.currentTimeMillis()));
    }


    /**
     * 是上一个调用开始后再次调用的延时（不用等待上一次调用完成）
     */
//    @Scheduled(fixedRate = 1000 * 1)
//    public void fixedRate() throws Exception
//    {
//        while (true) {
//
//            if (Thread.currentThread().isInterrupted()) {
//                System.out.println("i has interputed");
//                break;
//            }
//            System.out.println("-----");
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            Thread.yield();
//
//            System.out.println("执行测试fixedRate时间：" + new Date(System.currentTimeMillis()));
//        }
//    }

    /**
     * 上一个调用完成后再次调用的延时调用
     */
    //@Scheduled(fixedDelay = 1000 * 1)
    public void fixedDelay() throws Exception
    {
        Thread.sleep(3000);
        System.out.println("执行测试fixedDelay时间："+ new Date(System.currentTimeMillis()));
    }

    /**
     * 第一次被调用前的延时，单位毫秒
     */
    @Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000 * 2)
    public void initialDelay() throws Exception
    {
        System.out.println("执行测试initialDelay时间："+ new Date(System.currentTimeMillis()));
    }

}
