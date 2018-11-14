package finance.core.common.enums;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: BannerEnum.java, v0.1 2018/11/14 2:07 PM PM lili Exp $
 */
public enum BannerEnum {
    page(1, "页面"),
    bannerType(2, "banner类型"),
    ;
    private Integer code;
    private String msg;

    BannerEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "BannerEnum{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}