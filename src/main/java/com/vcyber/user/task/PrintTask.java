package com.vcyber.user.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
//public class PrintTask implements DisposableBean {
public class PrintTask {

    private final static Logger logger = LoggerFactory.getLogger(PrintTask.class);

//    @Override
//    public void destroy() throws Exception {
//        //关闭线程或线程池
//        ThreadPoolTaskScheduler scheduler = (ThreadPoolTaskScheduler)applicationContext.getBean("scheduler");
//        scheduler.shutdown();
//    }

    /**
     * 每小时的10分执行该方法
     * “15/5 * * * * ?” 每分钟的每1秒开始触发，每隔2秒触发一次
     */
//    @Scheduled(cron = "0 10 * * * *")
    @Scheduled(cron = "1/2 * * * * ?")
    public void cron() throws Exception
    {
        logger.info("执行测试cron时间："+ new Date(System.currentTimeMillis()));
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
        logger.info("执行测试fixedDelay时间："+ new Date(System.currentTimeMillis()));
        System.out.println("执行测试fixedDelay时间："+ new Date(System.currentTimeMillis()));
    }

    /**
     * 第一次被调用前的延时，单位毫秒
     */
    @Scheduled(initialDelay = 1000 * 10,fixedDelay = 1000 * 2)
    public void initialDelay() throws Exception
    {
        logger.info("执行测试initialDelay时间："+ new Date(System.currentTimeMillis()));
        System.out.println("执行测试initialDelay时间："+ new Date(System.currentTimeMillis()));
    }

}
