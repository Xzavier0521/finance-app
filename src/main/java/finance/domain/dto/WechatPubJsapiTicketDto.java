package finance.domain.dto;

/**
  * 微信公众号获得jsapi_ticket返回数据
  * @author panzhongkang
  * @date 2018/9/13 16:46
  */
public class WechatPubJsapiTicketDto {
    private String errcode;
    private String errmsg;
    private String ticket;
    private String expires_in;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "WechatPubJsapiTicketDto{" +
                "errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", ticket='" + ticket + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
