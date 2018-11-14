package finance.core.common.appstart;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * app启动监听类.
 * <pre>将app启动之后需要执行的动作加到这里</pre>
 * @author hewenbin
 * @version v1.0 2018年6月5日 下午1:44:41 hewenbin
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