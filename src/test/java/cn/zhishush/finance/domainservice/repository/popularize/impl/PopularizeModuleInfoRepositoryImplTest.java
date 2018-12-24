//package cn.zhishush.finance.domainservice.repository.popularize.impl;
//
//import cn.zhishush.finance.core.dal.dao.flash.InformationConfigMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PopularizeModuleInfoRepositoryImplTest {
//    @Resource
//    private InformationConfigMapper informationConfigServer;
//
//    @Test
//    public void query4Page() {
//        InformationConfig informationConfig = new InformationConfig();
//        informationConfig.setBank("dgf");
//        Map<String,Object> params = new HashMap<>();
//        params.put("params",informationConfig);
//
//        log.info("接受前端接受的参数{}",params);
//
//        List<InformationConfig> list = informationConfigServer.query(params);
//        log.info("接受的参数{}",list);
//        }
//
//
//
//}
