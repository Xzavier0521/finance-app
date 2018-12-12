package cn.zhishush.finance.domainservice.service.wechat;

import java.util.Map;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WechatBiz.java, v0.1 2018/11/26 6:58 PM lili Exp $
 */
public interface WechatBiz {

    Map<String, String> getSignature(String url);
}
