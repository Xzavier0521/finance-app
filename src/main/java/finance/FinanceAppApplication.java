package finance;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>启动类</p>
 *
 * @author lili
 * @version $Id: FinanceAppApplication.java, v0.1 2018/10/22 8:10 PM lili Exp $
 */
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("finance.core.dal.dao")
@EnableScheduling
@EnableAsync
public class FinanceAppApplication {

    public static ConfigurableApplicationContext appContect;

    public static void main(String[] args) {
        FinanceAppApplication.appContect = SpringApplication.run(FinanceAppApplication.class, args);
    }
}
