package cn.zhishush.finance.data;

import cn.zhishush.finance.api.model.vo.userinfo.UserParentInfoDetailVO;
import cn.zhishush.finance.api.model.vo.userinfo.UserWeChatInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: RestDataTest.java, v0.1 2018/12/18 2:23 PM PM lili Exp $
 */
@Slf4j
public class RestDataTest {

    @Test
    public void test1() throws JsonProcessingException {
        UserParentInfoDetailVO userParentInfoDetailVO = new UserParentInfoDetailVO();

        UserWeChatInfoVO userInfoVo = new UserWeChatInfoVO();
        userParentInfoDetailVO.setParentUserInfo(userInfoVo);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(userParentInfoDetailVO);
        log.info("json:{}", json);

    }
}
