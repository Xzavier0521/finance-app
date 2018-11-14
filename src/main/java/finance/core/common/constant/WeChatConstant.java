package finance.core.common.constant;

/**
 * <p>微信相关常量</p>
 * @author lili
 * @version $Id: WeChatConstant.java, v0.1 2018/10/23 11:07 PM lili Exp $
 */
public interface WeChatConstant {

    /**
     * 返回消息类型：文本
     */
    String RESP_MESSAGE_TYPE_TEXT      = "text";

    /**
     * 返回消息类型：音乐
     */
    String RESP_MESSAGE_TYPE_MUSIC     = "music";

    /**
     * 返回消息类型：图文
     */
    String RESP_MESSAGE_TYPE_NEWS      = "news";

    /**
     * 请求消息类型：文本
     */
    String REQ_MESSAGE_TYPE_TEXT       = "text";

    /**
     * 请求消息类型：图片
     */
    String REQ_MESSAGE_TYPE_IMAGE      = "image";
    /**
     * 返回消息类型：图片
     */
    String RESP_MESSAGE_TYPE_IMAGE     = "image";
    /**
     * 请求消息类型：链接
     */
    String REQ_MESSAGE_TYPE_LINK       = "link";

    /**
     * 请求消息类型：地理位置
     */
    String REQ_MESSAGE_TYPE_LOCATION   = "location";

    /**
     * 请求消息类型：音频
     */
    String REQ_MESSAGE_TYPE_VOICE      = "voice";

    /**
     * 请求消息类型：推送
     */
    String REQ_MESSAGE_TYPE_EVENT      = "event";
    /**
     * 事件类型：subscribe(订阅)
     */
    String EVENT_TYPE_SUBSCRIBE        = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    String EVENT_TYPE_UNSUBSCRIBE      = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    String EVENT_TYPE_CLICK            = "CLICK";
    /**
     * 微信用户列表查询最大记录数
     */
    int    MAX_PAGE_SIZE               = 10_000;
    /**
     * 保存微信公众号的关注用户的open_id
     */
    String WE_CHAT_FLOW_NUM            = "weChatPub:flowNumbers";

    String NOTICE_TOKEN                = "jinrongjia_weChat_pub_token";
    /**
     * 进入首页
     */
    String JINRONGJIA_INDEX            = "JINRONGJIA_INDEX";
    /**
     * 1分购物
     */
    String JINRONGJIA_EBAY             = "JINRONGJIA_EBAY";
    /**
     * 金主客服
     */
    String JINRONGJIA_CUSTOMER_SERVICE = "JINRONGJIA_CUSTOMER_SERVICE";

    /**
     * APP下载
     */
    String JINRONGJIA_APP              = "JINRONGJIA_APP";
    /**
     * 金榕家介绍
     */
    String JINRONGJIA_INTRODUCTION     = "JINRONGJIA_INTRODUCTION";
    /**
     * 打卡活动代码
     */
    String SIGN_ACTIVITY_CODE     = "0101";

    String WE_CHAT_PUB_TITLE           = "金榕家-专业的金融产品返利平台";
    String WE_CHAT_PUB_DESCRIPTION     = "金榕家以共享经济为核心，以科技技术为驱动的创新型平台。做为金融机构的入口返利平台，覆盖贷款、理财、信用卡、保险四大金融板块，以分享经济理念，打造金融领域专业的返利平台。";
    String WE_CHAT_PUB_PIC_URL         = "http://mmbiz.qpic.cn/mmbiz_jpg/kXjNrAN5B0rbMP7ialam4MIcMLbOg5SVmmBoVSRccGWyCIBNKx5ZmVmXIUrtYQe7g9houjugyIkicIETK4MrYcQA/0?wx_fmt=jpeg";
    String WE_CHAT_PUB_URL             = "http://mp.weixin.qq.com/s?__biz=MzAwNTgyMzM3Mg==&mid=100000053&idx=1&sn=a0e3cff4bee5a84afe48688b38f5b6a3&chksm=1b178fd62c6006c0ed81e3eab1e655d1cf36c95d7eaba86fae2c367de7915720254e6ebfba79#rd";
    String CUSTOMER_SERVICE_CONTENT    = "请在公众号直接留言咨询，会有人工为您一一解答哦~\n如未及时回复请添加金主微信号：17501656701进行咨询哦~\n感谢配合~";

    String APP_CONETENT                = "psTHtmXjnJpZ8G4DpajhAe7d8DEJy-HtybdV7M61ZPlyD-Q9PMeX-iWfGJTsRQSx";

    String APP_MEDIA_ID                = "FdRvYwzOLqKpiuTkwMaY6WJ1xyfY_-AZl7OgG5ROraY";

    String QR_GET_URL                  = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    String WEB_CHAT_TICKET             = "TICKET";
    Long   QR_EXPIRE_SECONDS           = 2592000L;
}
