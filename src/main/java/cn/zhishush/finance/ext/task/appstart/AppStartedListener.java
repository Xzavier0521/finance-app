package cn.zhishush.finance.ext.task.appstart;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <p> app启动监听类.将app启动之后需要执行的动作加到这里</p>
 * @author lili
 * @version 1.0: AppStartedListener.java, v0.1 2018/12/4 6:42 PM lili Exp $
 */
@Component
public class AppStartedListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger logger = LoggerFactory.getLogger(AppStartedListener.class);

    @Resource
    private AppStartTask        appStartTask;

    /**
     * 应用成功启动之后会执行该方法.
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        appStartTask.start();
        logger.warn("应用启动之后的任务成功启动");
    }

}