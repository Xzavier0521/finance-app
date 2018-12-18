package cn.zhishush.finance.web.controller.noauth.business;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.business.BusinessCooperationVO;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.dal.dao.business.BusinessCooperationDAO;
import cn.zhishush.finance.core.dal.dataobject.business.BusinessCooperationDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>商务合</p>
 *
 * @author lili
 * @version $Id: BusinessCooperationController.java, v0.1 2018/10/29 5:24 PM lili Exp $
 */
@Slf4j
@RequestMapping("/businessCooperation")
@RestController
public class BusinessCooperationController {

    @Resource
    private BusinessCooperationDAO businessCooperationDAO;

    @PostMapping("/save")
    public ResponseResult<Object> saveBusinessInfo(@RequestBody BusinessCooperationVO businessCooperation) {
        try {
            BusinessCooperationDO businessCooperationDO = new BusinessCooperationDO();
            if (Objects.isNull(businessCooperation)) {
                return ResponseResult.error(CodeEnum.systemError);
            }
            businessCooperationDO.setRealName(businessCooperation.getRealName());
            businessCooperationDO.setMobilePhone(businessCooperation.getMobilePhone());
            businessCooperationDAO.insertSelective(businessCooperationDO);
            return ResponseResult.success(null);
        } catch (final Exception e) {
            return ResponseResult.error(CodeEnum.systemError);
        }
    }
}
