package finance.core.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;

import finance.core.common.constants.WeChatConstant;
import finance.domain.weixin.Message;
import finance.domain.weixin.TextMessage;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: MessageUtils.java, v0.1 2018/10/22 4:38 PM lili Exp $
 */
public class MessageUtils {

    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();
        inputStream = null;
        return map;
    }

    /**
     * 将xml转换成map集合
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException,
                                                                           DocumentException {
        Map<String, String> map = new HashMap<>();
        //使用dom4j解析xml
        SAXReader reader = new SAXReader();
        //从request中获取输入流
        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);
        //获取根元素
        Element root = doc.getRootElement();
        //获取所有的节点
        List<Element> list = root.elements();
        for (Element e : list) {
            map.put(e.getName(), e.getText());
        }
        ins.close(); //关流
        return map;
    }

    /**
     * 关注初始化消息内容
     * @return String
     */
    public static String welcomeText() {
        StringBuffer b = new StringBuffer();
        b.append("小主，您来了，我是您的贴身金融小管家—榕麽麽。金榕家是一家贷款、办卡、理财、保险的一站式金融服务返利平台，让人人都是金融受益者。\uD83C\uDF39\n");
        b.append("\n");
        b.append("金榕家【赚钱攻略】\n");
        b.append(
            "<a href='https://finance.zhishush.cn/finance-h5/msite/#/login?activityName=jtxyk&am_id=jinrongjia2'>首次核卡成功奖励100元\uD83C\uDF81</a>\n");
        b.append("\n");
        b.append("金榕家信用卡、贷款推广指南\n");
        b.append(
            "<a href='https://finance.zhishush.cn/finance-h5/msite/#/login?activityName=jdbt&am_id=jinrongjia2'>激活奖励25元\uD83C\uDF81</a>\n");
        b.append("\n");
        b.append("如何一天锁粉1000人，让别人主动加你？\n");
        b.append(
            "<a href='https://finance.zhishush.cn/finance-h5/msite/#/login?activityName=jdbt&am_id=jinrongjia2'>激活奖励25元\uD83C\uDF81</a>\n");
        b.append("\n");
        b.append("热门活动等您来参加：\n");
        b.append("\n");
        b.append("新用户首次核卡成功奖励100元\n");
        b.append("\n");
        b.append(
            "<a href='https://finance.zhishush.cn/finance-h5/msite/#/login?activityName=jdbt&am_id=jinrongjia2'>激活奖励25元\uD83C\uDF81</a>\n");
        b.append("激活京东白条返利25元\n");
        b.append("\n");
        b.append(
            "<a href='https://finance.zhishush.cn/finance-h5/msite/#/login?activityName=jdbt&am_id=jinrongjia2'>激活奖励25元\uD83C\uDF81</a>\n");
        b.append("\n");
        b.append("拼手速，每天3轮红包雨，金币抢不停\n");
        b.append("\n");
        b.append(
            "<a href='https://finance.zhishush.cn/finance-h5/msite/#/login?activityName=jdbt&am_id=jinrongjia2'>激活奖励25元\uD83C\uDF81</a>\n");
        b.append("有问题可以直接留言或者添加金主微信号17501656701进行咨询哦~");
        return b.toString();
    }

    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage message = new TextMessage();
        message.setToUserName(fromUserName);
        message.setFromUserName(toUserName);
        message.setMsgType(WeChatConstant.REQ_MESSAGE_TYPE_TEXT);
        message.setContent(content);
        message.setCreateTime(System.currentTimeMillis());
        return objectToXml(message);
    }

    public static String objectToXml(Message message) {
        XStream xStream = new XStream();
        //xml根节点替换成<xml> 默认是Message的包名
        xStream.alias("xml", message.getClass());
        return xStream.toXML(message);
    }

}