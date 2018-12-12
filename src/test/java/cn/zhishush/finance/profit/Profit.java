package cn.zhishush.finance.profit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.zhishush.finance.domainservice.service.finance.trans.TransBiz;

/**
 * @author yaolei
 * @Title: Profit
 * @ProjectName finance-app
 * @Description: TODO
 * @date 2018/7/10上午10:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Profit {

    @Autowired
    public TransBiz p;

    @Test
    public void testMyProfit() {
//        ResponseResult<List<ProfitDO>> res = p.myProfit(1l);
//        System.out.println(JSON.toJSON(res));
    }}
