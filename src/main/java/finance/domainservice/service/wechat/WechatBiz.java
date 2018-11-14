package finance.domainservice.service.wechat;

import java.util.Map;

public interface WechatBiz {
    Map<String,String> getSignature(String url);
}
