package com.xiaobu.base.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author xiaobu
 * @version JDK1.8.0_171
 * @date on  2018/11/23 16:26
 * @description V1.0  @Async 代表该任务可以进行异步工作，由原本的串行改为并行
 */
@Component
@Slf4j
public class TastkDemo1 {
    /**
     * @author xiaobu
     * @date 2018/11/23 16:48
     * @return void
     * @descprition  cron = "0/1 * * * * *" 每个一秒执行一次 执行周期为3s
     * 若开启 @Async则一秒执行一次
     * @version 1.0
     */
   /* @Async
    @Scheduled(cron = "0/1 * * * * *")
    public void scheduled1() throws InterruptedException {
        Thread.sleep(3000);
        log.info("scheduled1 每1秒执行一次：{}", LocalDateTime.now());
    }*/

    /*
     * @author xiaobu
     * @date 2018/11/23 16:41
     * @return void
     * @descprition   fixedRate每隔多少秒执行一次
     * @version 1.0    若开启 @Async则一秒执行一次
     */
   @Async
    @Scheduled(fixedRate = 1000)
    public void scheduled2() throws InterruptedException {
        Thread.sleep(3000);
        log.info("scheduled2 每1秒执行一次：{}", LocalDateTime.now());
    }

    /*
     * @author xiaobu
     * @date 2018/11/23 16:38
     * @return void
     * @descprition   fixedDelay当前任务执行完毕后等待多久继续下次任务 过8秒之后再执行
     * @version 1.0
     */
  /*  @Scheduled(fixedDelay = 3000)
    public void scheduled3() throws InterruptedException {
        Thread.sleep(5000);
        log.info("scheduled3 上次执行完毕后隔3秒继续执行：{}", LocalDateTime.now());
    }*/
}
