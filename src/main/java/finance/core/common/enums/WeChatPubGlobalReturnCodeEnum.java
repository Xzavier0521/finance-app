package finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;
/**
 * <p>微信公众号全局返回码</p>
 * @author lili
 * @version $Id: WeChatPubGlobalReturnCodeEnum.java, v0.1 2018/10/29 10:56 PM lili Exp $
 */
public enum WeChatPubGlobalReturnCodeEnum {
                                    /**
                                     * 成功
                                     */
    REQUEST_SUCCESSFUL("请求成功", "0"),
    SYSTEM_BUSY("系统繁忙", "-1"),
    APP_SECRET_ERROR("40001-AppSecret错误", "40001"),
    INVALID_CERTIFICATE_TYPE("40002-不合法的凭证类型", "40002"),
    ILLEGAL_OPENID("40003-不合法的 OpenID ,请开发者确认 OpenID （该用户）是否已关注公众号,或是否是其他公众号的 OpenID", "40003"),
    INVALID_MEDIA_FILE_TYPE("40004-不合法的媒体文件类型", "40004"),
    INVALID_FILE_TYPE("40005-不合法的文件类型", "40005"),
    INVALID_FILE_SIZE("40006-不合法的文件大小", "40006"),
    ILLEGAL_MEDIA_FILE_ID("40007-不合法的媒体文件 id", "40007"),
    INVALID_MESSAGE_TYPE("40008-不合法的消息类型", "40008"),
    INVALID_IMAGE_FILE_SIZE("40009-不合法的图片文件大小", "40009"),
    INVALID_VOICE_FILE_SIZE("40010-不合法的语音文件大小", "40010"),
    INVALID_VIDEO_FILE_SIZE("40011-不合法的视频文件大小", "40011"),
    INVALID_THUMBNAIL_FILE_SIZE("40012-不合法的缩略图文件大小", "40012"),
    INVALID_APPID("40013-不合法的AppID,请开发者检查 AppID 的正确性,避免异常字符,注意大小写", "40013"),
    INVALID_ACCESS_TOKEN("40014-不合法的 access_token ，请开发者认真比对 access_token 的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口", "40014"),
    ILLEGAL_MENU_TYPE("40015-不合法的菜单类型", "40015"),
    THE_NUMBER_OF_ILLEGAL_BUTTONS_1("40016-不合法的按钮个数", "40016"),
    THE_NUMBER_OF_ILLEGAL_BUTTONS_2("40017-不合法的按钮个数", "40017"),
    ILLEGAL_BUTTON_NAME_LENGTH("40018-不合法的按钮名字长度", "40018"),
    ILLEGAL_BUTTON_KEY_LENGTH("40019-不合法的按钮 KEY 长度", "40019"),
    ILLEGAL_BUTTON_URL_LENGTH("40020-不合法的按钮 URL 长度", "40020"),
    ILLEGAL_MENU_VERSION_NUMBER("40021-不合法的菜单版本号", "40021"),
    INVALID_SUBMENU_LEVEL("40022-不合法的子菜单级数", "40022"),
    THE_NUMBER_OF_ILLEGAL_SUBMENU_BUTTONS("40023-不合法的子菜单按钮个数", "40023"),
    INVALID_SUBMENU_BUTTON_TYPE("40024-不合法的子菜单按钮类型", "40024"),
    ILLEGAL_SUBMENU_BUTTON_NAME_LENGTH("40025-不合法的子菜单按钮名字长度", "40025"),
    INVALID_SUBMENU_BUTTON_KEY_LENGTH("40026-不合法的子菜单按钮 KEY 长度", "40026"),
    INVALID_SUBMENU_BUTTON_URL_LENGTH("40027-不合法的子菜单按钮 URL 长度", "40027"),
    ILLEGAL_CUSTOM_MENU_USAGE_USER("40028-不合法的自定义菜单使用用户", "40028"),
    INVALID_OAUTH_CODE("40029-不合法的 oauth_code", "40029"),
    ILLEGAL_REFRESH_TOKEN("40030-不合法的 refresh_token", "40030"),
    ILLEGAL_OPENID_LIST("40031-不合法的 openid 列表", "40031"),
    ILLEGAL_OPENID_LIST_LENGTH("40032-不合法的 openid 列表长度", "40032"),
    ILLEGAL_REQUEST_CHARACTER("40033-不合法的请求字符，不能包含 \\uxxxx 格式的字符", "40033"),
    INVALID_PARAMETERS("40035-不合法的参数", "40035"),
    INVALID_REQUEST_FORMAT("40038-不合法的请求格式", "40038"),
    INVALID_URL_LENGTH("40039-不合法的 URL 长度", "40039"),
    INVALID_GROUP_ID("40050-不合法的分组 id", "40050"),
    THE_GROUP_NAME_IS_ILLEGAL_1("40051-分组名字不合法", "40051"),
    THE_SPECIFIED_ARTICLE_IDX_IS_ILLEGAL("40060-删除单篇图文时，指定的 article_idx 不合法", "40060"),
    THE_GROUP_NAME_IS_ILLEGAL_2("40117-分组名字不合法", "40117"),
    ILLEGAL_MEDIA_ID_SIZE("40118-media_id 大小不合法", "40118"),
    WRONG_BUTTON_TYPE_1("40119-button 类型错误", "40119"),
    WRONG_BUTTON_TYPE_2("40120-button 类型错误", "40120"),
    INVALID_MEDIA_ID_TYPE("40121-不合法的 media_id 类型", "40121"),
    MICRO_SIGNAL_IS_ILLEGAL("40132-微信号不合法", "40132"),
    UNSUPPORTED_IMAGE_FORMAT("40137-不支持的图片格式", "40137"),
    ADD_ANOTHER_PUBLIC_NUMBER_HOMEPAGE_LINK("40155-请勿添加其他公众号的主页链接", "40155"),
    MISSING_ACCESS_TOKEN_PARAMETER("41001-缺少 access_token 参数", "41001"),
    MISSING_APPID_PARAMETER("41002-缺少 appid 参数", "41002"),
    MISSING_REFRESH_TOKEN_PARAMETER("41003-缺少 refresh_token 参数", "41003"),
    MISSING_SECRET_PARAMETER("41004-缺少 secret 参数", "41004"),
    MISSING_MULTIMEDIA_FILE_DATA("41005-缺少多媒体文件数据", "41005"),
    MISSING_MEDIA_ID_PARAMETER("41006-缺少 media_id 参数", "41006"),
    MISSING_SUBMENU_DATA("41007-缺少子菜单数据", "41007"),
    MISSING_OAUTH_CODE("41008-缺少 oauth code", "41008"),
    MISSING_OPENID("41009-缺少 openid", "41009"),
    ACCESS_TOKEN_TIMEOUT("42001-access_token 超时，请检查 access_token 的有效期，请参考基础支持 - 获取 access_token 中，对 access_token 的详细机制说明", "42001"),
    REFRESH_TOKEN_TIMEOUT("42002-refresh_token 超时", "42002"),
    OAUTH_CODE_TIMEOUT("42003-oauth_code 超时", "42003"),
    USER_MODIFIED_WECHAT_PASSWORD("42007-用户修改微信密码， accesstoken 和 refreshtoken 失效，需要重新授权", "42007"),
    NEED_GET_REQUEST("43001-需要 GET 请求", "43001"),
    NEED_POST_REQUEST("43002-需要 POST 请求", "43002"),
    NEED_HTTPS_REQUEST("43003-需要 HTTPS 请求", "43003"),
    NEED_THE_ATTENTION_OF_THE_RECIPIENT("43004-需要接收者关注", "43004"),
    NEED_A_FRIEND("43005-需要好友关系", "43005"),
    NEED_TO_REMOVE_THE_RECEIVER_FROM_THE_BLACK_LIST("43019-需要将接收者从黑名单中移除", "43019"),
    MULTIMEDIA_FILE_IS_EMPTY("44001-多媒体文件为空", "44001"),
    POST_PACKET_IS_EMPTY("44002-POST 的数据包为空", "44002"),
    IMAGE_MESSAGE_IS_EMPTY("44003-图文消息内容为空", "44003"),
    TEXT_MESSAGE_IS_EMPTY("44004-文本消息内容为空", "44004"),
    MULTIMEDIA_FILE_SIZE_EXCEEDS_LIMIT("45001-多媒体文件大小超过限制", "45001"),
    MESSAGE_CONTENT_EXCEEDS_LIMITS("45002-消息内容超过限制", "45002"),
    TITLE_FIELD_EXCEEDS_LIMIT("45003-标题字段超过限制", "45003"),
    DESCRIPTION_FIELD_EXCEEDS_LIMIT("45004-描述字段超过限制", "45004"),
    LINK_FIELD_EXCEEDS_LIMIT("45005-链接字段超过限制", "45005"),
    IMAGE_LINK_FIELD_EXCEEDS_LIMIT("45006-图片链接字段超过限制", "45006"),
    VOICE_PLAY_TIME_EXCEEDS_LIMIT("45007-语音播放时间超过限制", "45007"),
    GRAPHIC_MESSAGE_EXCEEDS_LIMIT("45008-图文消息超过限制", "45008"),
    INTERFACE_CALL_EXCEEDS_LIMIT("45009-接口调用超过限制", "45009"),
    CREATED_MENUS_EXCEEDS_THE_LIMIT("45010-创建菜单个数超过限制", "45010"),
    API_CALLS_TOO_OFTEN("45011-API 调用太频繁，请稍候再试", "45011"),
    RESPONSE_TIME_EXCEEDS_LIMIT("45015-回复时间超过限制", "45015"),
    SYSTEM_GROUPING("45016-系统分组，不允许修改", "45016"),
    GROUP_NAME_IS_TOO_LONG("45017-分组名字过长", "45017"),
    THE_NUMBER_OF_GROUPS_EXCEEDS_THE_LIMIT("45018-分组数量超过上限", "45018"),
    CUSTOMER_SERVICE_INTERFACES_DOWN_EXCEEDS_THE_UPPER_LIMIT("45047-客服接口下行条数超过上限", "45047"),
    NO_MEDIA_DATA_EXISTS("46001-不存在媒体数据", "46001"),
    NON_EXISTENT_MENU_VERSION("46002-不存在的菜单版本", "46002"),
    NON_EXISTENT_MENU_DATA("46003-不存在的菜单数据", "46003"),
    NON_EXISTENT_USER("46004-不存在的用户", "46004"),
    PARSING_JSON_XML_CONTENT_ERROR("47001-解析 JSON/XML 内容错误", "47001"),
    API_FEATURE_NOT_AUTHORIZED("48001-api 功能未授权，请确认公众号已获得该接口，可以在公众平台官网 - 开发者中心页中查看接口权限", "48001"),
    FANS_REJECTED_MESSAGE("48002-粉丝拒收消息（粉丝在公众号选项中，关闭了 '接收消息' ）", "48002"),
    API_INTERFACE_BANNED("48004-api 接口被封禁，请登录 mp.weixin.qq.com 查看详情", "48004"),
    API_DISABLES_DELETION("48005-api 禁止删除被自动回复和自定义菜单引用的素材", "48005"),
    API_SUPPRESS_ZERO_CALLS("48006-api 禁止清零调用次数，因为清零次数达到上限", "48006"),
    NO_SEND_PERMISSION_FOR_THIS_TYPE_OF_MESSAGE("48008-没有该类型消息的发送权限", "48008"),
    THE_USER_DID_NOT_AUTHORIZE_THIS_API("50001-用户未授权该 api", "50001"),
    USER_LIMITED("50002-用户受限，可能是违规后接口被封禁", "50002"),
    USER_DID_NOT_PAY_ATTENTION_TO_THE_PUBLIC_NUMBER("50005-用户未关注公众号", "50005"),
    INVALID_PARAMETER("61451-参数错误", "61451"),
    INVALID_KF_ACCOUNT("61452-无效客服账号", "61452"),
    KF_ACCOUNT_EXSITED("61453-客服帐号已存在", "61453"),
    INVALID_KF_ACOUNT_LENGTH("61454-客服帐号名长度超过限制 ( 仅允许 10 个英文字符，不包括 @ 及 @ 后的公众号的微信号 )", "61454"),
    ILLEGAL_CHARACTER_IN_KF_ACCOUNT("61455-客服帐号名包含非法字符 ( 仅允许英文 + 数字 )", "61455"),
    KF_ACCOUNT_COUNT_EXCEEDED("61456-客服帐号个数超过限制 (10 个客服账号 )", "61456"),
    INVALID_AVATAR_FILE_TYPE("61457-无效头像文件类型", "61457"),
    SYSTEM_ERROR("61450-系统错误", "61450"),
    DATE_FORMAT_IS_WRONG("61500-日期格式错误", "61500"),
    NO_PERSONALIZED_MENU_FOR_THIS_MENUID("65301-不存在此 menuid 对应的个性化菜单", "65301"),
    NO_CORRESPONDING_USER("65302-没有相应的用户", "65302"),
    NO_DEFAULT_MENU("65303-没有默认菜单，不能创建个性化菜单", "65303"),
    MATCHRULE_INFORMATION_IS_EMPTY("65304-MatchRule 信息为空", "65304"),
    LIMITED_NUMBER_OF_PERSONALIZED_MENUS("65305-个性化菜单数量受限", "65305"),
    NOT_SUPPORT_PERSONALIZED_MENUS("65306-不支持个性化菜单的帐号", "65306"),
    PERSONALIZED_MENU_INFORMATION_IS_EMPTY("65307-个性化菜单信息为空", "65307"),
    CONTAINS_NO_RESPONSE_TYPE_BUTTON("65308-包含没有响应类型的 button", "65308"),
    PERSONALIZED_MENU_SWITCH_IS_OFF("65309-个性化菜单开关处于关闭状态", "65309"),
    COUNTRY_INFORMATION_CANNOT_BE_EMPTY("65310-填写了省份或城市信息，国家信息不能为空", "65310"),
    PROVINCIAL_INFORMATION_CANNOT_BE_EMPTY("65311-填写了城市信息，省份信息不能为空", "65311"),
    ILLEGAL_COUNTRY_INFORMATION("65312-不合法的国家信息", "65312"),
    ILLEGAL_PROVINCE_INFORMATION("65313-不合法的省份信息", "65313"),
    ILLEGAL_CITY_INFORMATION("65314-不合法的城市信息", "65314"),
    TOO_MANY_DOMAIN_NAME_JUMPS("65316-该公众号的菜单设置了过多的域名外跳（最多跳转到 3 个域名的链接）", "65316"),
    ILLEGAL_URL("65317-不合法的 URL", "65317"),
    POST_DATA_PARAMETER_IS_ILLEGAL("9001001-POST 数据参数不合法", "9001001"),
    REMOTE_SERVICE_UNAVAILABLE("9001002-远端服务不可用", "9001002"),
    TICKET_IS_ILLEGAL("9001003-Ticket 不合法", "9001003"),
    FAILED_TO_SHAKE_PERIPHERAL_USER_INFORMATION("9001004-获取摇周边用户信息失败", "9001004"),
    FAILED_TO_GET_BUSINESS_INFORMATION("9001005-获取商户信息失败", "9001005"),
    FAILED_TO_GET_OPENID("9001006-获取 OpenID 失败", "9001006"),
    MISSING_UPLOAD_FILE("9001007-上传文件缺失", "9001007"),
    THE_UPLOADED_FILE_IS_OF_AN_ILLEGAL_FILE_TYPE("9001008-上传素材的文件类型不合法", "9001008"),
    THE_FILE_SIZE_OF_THE_UPLOADED_MATERIAL_IS_ILLEGAL("9001009-上传素材的文件尺寸不合法", "9001009"),
    UPLOAD_FAILED("9001010-上传失败", "9001010"),
    ACCOUNT_IS_ILLEGAL("9001020-帐号不合法", "9001020"),
    DEVICE_ACTIVATION_RATE_IS_LESS_THAN_FIFTY_PERCENT("9001021-已有设备激活率低于 50% ，不能新增设备", "9001021"),
    THE_NUMBER_OF_DEVICE_APPLICATIONS_IS_ILLEGAL("9001022-设备申请数不合法，必须为大于 0 的数字", "9001022"),
    EXISTING_DEVICE_ID_APPLICATION_IN_REVIEW("9001023-已存在审核中的设备 ID 申请", "9001023"),
    QUERY_DEVICE_IDS_CANNOT_EXCEED_FIFTY("9001024-一次查询设备 ID 数量不能超过 50", "9001024"),
    DEVICE_ID_IS_ILLEGAL("9001025-设备 ID 不合法", "9001025"),
    PAGE_ID_IS_ILLEGAL("9001026-页面 ID 不合法", "9001026"),
    PAGE_PARAMETER_IS_ILLEGAL("9001027-页面参数不合法", "9001027"),
    DELETED_PAGE_IDS_CANNOT_EXCEED_TEN("9001028-一次删除页面 ID 数量不能超过 10", "9001028"),
    PAGE_IS_ALREADY_APPLIED_TO_THE_DEVICE("9001029-页面已应用在设备中，请先解除应用关系再删除", "9001029"),
    ONE_QUERY_PAGE_ID_CANNOT_EXCEED_FIFTY("9001030-一次查询页面 ID 数量不能超过 50", "9001030"),
    THE_TIME_INTERVAL_IS_ILLEGAL("9001031-时间区间不合法", "9001031"),
    SAVE_DEVICE_AND_PAGE_BINDING_PARAMETERS_ERROR("9001032-保存设备与页面的绑定关系参数错误", "9001032"),
    STORE_ID_IS_ILLEGAL("9001033-门店 ID 不合法", "9001033"),
    DEVICE_NOTE_INFORMATION_IS_TOO_LONG("9001034-设备备注信息过长", "9001034"),
    DEVICE_APPLICATION_PARAMETERS_ARE_ILLEGAL("9001035-设备申请参数不合法", "9001035"),
    QUERY_START_VALUE_IS_NOT_LEGAL("9001036-查询起始值 begin 不合法", "9001036");

    private String code;
    private String desc;

    WeChatPubGlobalReturnCodeEnum(String desc,String code) {
        this.code = code;
        this.desc = desc;
    }

    public static WeChatPubGlobalReturnCodeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            WeChatPubGlobalReturnCodeEnum[] var1 = values();
            for (WeChatPubGlobalReturnCodeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
