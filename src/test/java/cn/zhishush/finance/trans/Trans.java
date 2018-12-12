package cn.zhishush.finance.trans;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.fastjson.JSON;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeProfit.FinanceProfitVO;
import cn.zhishush.finance.domainservice.service.finance.order.OrderBiz;
import cn.zhishush.finance.domainservice.service.finance.trans.TransBiz;
import cn.zhishush.finance.domainservice.service.sms.SmsBiz;

/**
 * @author yaolei
 * @Title: Trans
 * @ProjectName finance-app
 * @Description: TODO
 * @date 2018/7/10下午8:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class Trans {

    @Autowired
    public OrderBiz order;
    @Autowired
    public TransBiz trans;
    @Autowired
    public SmsBiz   sms;

    @Test
    public void testWithDraw() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", "3");
        map.put("money", "1");
        //trans.withdraw(map);
    }

    @Test
    public void testMyProfit() {
        Page<FinanceProfitVO> page = new Page<>(300, 1L);
        System.out.println(JSON.toJSONString(trans.myProfit(185L, page)));
    }

    @Test
    public void testTransRecords() {
        System.out.println(JSON.toJSONString(trans.transRecords(185l, new Page<>(10, 1L))));
        //  trans.transRecords(185l);
    }

    @Test
    public void testCharge() {
        trans.recharge(31l, "测试");
    }
}
