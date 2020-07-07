package com.kpy.springboot.Scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @auther: kpy
 * @version: 1.0
 * @Package: com.kpy.springboot.Scheduler
 * @data: 2020-6-18 22:17
 * @discription:
 **/
@Component
public class SchedulerTask {

    private static Logger logger = LoggerFactory.getLogger(SchedulerTask.class);

    private int count = 0;

    //  * 代表每隔1秒钟触发；
    //  , 代表在指定的秒数触发，比如”0,15,45”代表0秒、15秒和45秒时触发任务
    //  - 代表在指定的范围内触发，比如”25-45”代表从25秒开始触发到45秒结束触发，每隔1秒触发1次
    //  / 代表触发步进(step)，”/”前面的值代表初始值(““等同”0”)，后面的值代表偏移量，比如”0/20”或者”/20”代表从0秒钟开始，每隔20秒钟触发1次，即0秒触发1次，20秒触发1次，40秒触发1次；”5/20”代表5秒触发1次，25秒触发1次，45秒触发1次；”10-45/20”代表在[10,45]内步进20秒命中的时间点触发，即10秒触发1次，30秒触发1次
    @Scheduled(cron = "*/5 * * * * ?")
    private void process() {
        logger.debug("count:{}", count++);
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 60000)
    private void showNowTimeProcess() {
        logger.debug("现在时间:{}", dateFormat.format(new Date()));
    }

}
