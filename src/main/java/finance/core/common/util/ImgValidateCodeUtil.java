package finance.core.common.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 图片验证码生成工具类.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月6日 上午11:16:32 hewenbin
 */
public class ImgValidateCodeUtil {
	private final static Logger logger = LoggerFactory.getLogger(ImgValidateCodeUtil.class);

	// 字符的高度和宽度，单位为像素
	private int wordHeight = 10;

	private int wordWidth = 15;

	// 字符大小
	// private int fontSize = 16;

	// 最大字符串个数
	private static final int MAX_CHARCOUNT = 16;

	// 垂直方向起始位置
	private final int initypos = 5;

	// 要生成的字符个数，由工厂方法得到
	private int charCount = 0;

	// 颜色数组，绘制字串时随机选择一个
	private static final Color[] CHAR_COLOR = {Color.RED, Color.BLUE, Color.MAGENTA, Color.blue};

	// 随机数生成器
	private Random r = new Random();

	/**
	 * 生成图像的格式常量，JPEG格式,生成为文件时扩展名为.jpg； 输出到页面时需要设置MIME type 为image/jpeg
	 */
	public static String GRAPHIC_JPEG = "JPEG";

	/**
	 * 生成图像的格式常量，PNG格式,生成为文件时扩展名为.png； 输出到页面时需要设置MIME type 为image/png
	 */
	public static String GRAPHIC_PNG = "PNG";

	// 用工厂方法创建对象
	protected ImgValidateCodeUtil(int charCount) {
		this.charCount = charCount;
	}

	/**
	 * 创建对象的工厂方法
	 * 
	 * @param charCount
	 *            要生成的字符个数，个数在1到16之间
	 * @return 返回RandomGraphic对象实例
	 * @throws Exception
	 *             参数charCount错误时抛出
	 * 
	 */
	public static ImgValidateCodeUtil createInstance(int charCount) throws Exception {
		if (charCount < 1 || charCount > MAX_CHARCOUNT) {
			throw new Exception("Invalid parameter charCount,charCount should between in 1 and 16");
		}
		return new ImgValidateCodeUtil(charCount);
	}

	/**
	 * 随机生成一个数字串，并以图像方式绘制，绘制结果输出到流out中
	 * 
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	public String drawNumber(String graphicFormat, OutputStream out) throws IOException {

		// 随机生成的串的值

		String charValue = "";

		/* charValue = randNumber(); */
		charValue = randAlphaStr(4);
		return draw(charValue, graphicFormat, out);
	}

	/**
	 * 随机生成一个字母串，并以图像方式绘制，绘制结果输出到流out中
	 * 
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	public String drawAlpha(String graphicFormat, OutputStream out) throws IOException {

		// 随机生成的串的值

		String charValue = "";

		charValue = randAlphaStr(charCount);

		return draw(charValue, graphicFormat, out);

	}

	// 给定范围获得随机颜色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 以图像方式绘制字符串，绘制结果输出到流out中
	 * 
	 * @param charValue
	 *            要绘制的字符串
	 * @param graphicFormat
	 *            设置生成的图像格式，值为GRAPHIC_JPEG或GRAPHIC_PNG
	 * @param out
	 *            图像结果输出流
	 * @return 随机生成的串的值
	 * @throws IOException
	 */
	protected String draw(String charValue, String graphicFormat, OutputStream out) throws IOException {
		int width = (charCount + 2) * wordWidth;
		int height = wordHeight * 3;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 创建一个随机数生成器类。
		Random random = new Random();
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(getColor(100));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("宋体", Font.BOLD, 18));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.setColor(getColor(25));
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 绘制charValue,每个字符颜色随机
		for (int i = 0; i < charCount; i++) {
			String c = charValue.substring(i, i + 1);
			Color color = CHAR_COLOR[randomInt(0, CHAR_COLOR.length)];
			g.setColor(color);
			int xpos = (i + 1) * wordWidth;
			// 垂直方向上随机
			int ypos = randomInt(initypos + wordHeight, initypos + wordHeight * 2);
			g.drawString(c, xpos, ypos);
		}

		g.dispose();
		image.flush();
		// 输出到流
		ImageIO.write(image, graphicFormat, out);
		return charValue;
	}

	/***
	 * 随机返回一种颜色,透明度0~255 0表示全透
	 * 
	 * @return 随机返回一种颜色
	 * @param alpha
	 *            透明度0~255 0表示全透
	 */
	private Color getColor(int alpha) {
		int R = (int) (Math.random() * 255);
		int G = (int) (Math.random() * 255);
		int B = (int) (Math.random() * 255);
		return new Color(R, G, B, alpha);
	}

	public String drawInputstr(int num, String graphicFormat, OutputStream out) throws IOException {
		String charValue = randAlphaStr(num);
		int width = (charCount + 2) * wordWidth;
		int height = wordHeight * 3;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 创建一个随机数生成器类。
		Random random = new Random();
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景色
		g.setColor(getColor(80));
		g.fillRect(0, 0, width, height);
		// 设置干扰点
		CreateRandomPoint(width, height, 50, g, 255);
		// 设定字体
		g.setFont(new Font("宋体", Font.BOLD, 18));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 135; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.setColor(getColor(200));
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 绘制charValue,每个字符颜色随机
		for (int i = 0; i < charCount; i++) {
			String c = charValue.substring(i, i + 1);
			Color color = CHAR_COLOR[randomInt(0, CHAR_COLOR.length)];
			g.setColor(color);
			int xpos = (i + 1) * wordWidth;
			// 垂直方向上随机
			int ypos = randomInt(initypos + wordHeight, initypos + wordHeight * 2);
			g.drawString(c, xpos, ypos);
		}

		g.dispose();
		image.flush();
		// 输出到流
		ImageIO.write(image, graphicFormat, out);
		return charValue;
	}

	// 生成随机数字串
	protected String randNumber() {
		String charValue = "";
		for (int i = 0; i < charCount; i++) {
			charValue += String.valueOf(randomInt(0, 10));
		}
		return charValue;
	}

	// 生成随机字符串
	private String randAlphaStr(int num) {
		StringBuffer charValue = new StringBuffer();
		// String str = "abcdefhkmnprstuvwxyzABCDEFGHKMNOPQRSTUVWXYZ2345678";
		String str = "123456789";
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			int number = random.nextInt(str.length());
			charValue.append(str.charAt(number));
		}
		return charValue.toString();
	}

	/**
	 * 返回[from,to)之间的一个随机整数
	 * 
	 * @param from
	 *            起始值
	 * @param to
	 *            结束值
	 * @return [from,to)之间的一个随机整数
	 */
	protected int randomInt(int from, int to) {
		return from + r.nextInt(to - from);
	}

	/**
	 * 随机产生干扰点
	 * 
	 * @param width
	 * @param height
	 * @param many
	 * @param g
	 * @param alpha
	 *            透明度0~255 0表示全透
	 */
	private void CreateRandomPoint(int width, int height, int many, Graphics g, int alpha) { // 随机产生干扰点
		Random random = new Random();
		for (int i = 0; i < many; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			g.setColor(getColor(alpha));
			g.drawOval(x, y, random.nextInt(3), random.nextInt(3));
		}
	}

	/**
	 * 生成Base64图片验证码(PNG).
	 * 
	 * @param StringNum
	 *            验证码长度
	 * @return
	 * @author hewenbin
	 * @version ImgValidateCodeUtil.java, v1.0 2018年7月6日 上午11:41:52 hewenbin
	 */
	public static ImgValidate getRandomCode(int StringNum) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		ImgValidate validate = new ImgValidate();
		try {
			String imgCode = ImgValidateCodeUtil.createInstance(StringNum).drawAlpha(ImgValidateCodeUtil.GRAPHIC_PNG,
					bs);
			String base64Str = Base64.getEncoder().encodeToString(bs.toByteArray());
			validate.setBase64Str(base64Str);
			validate.setValue(imgCode);
		} catch (Exception e) {
			logger.error(LogUtil.getFormatLog("StringNum:" + StringNum, "生成图片验证码异常"), e);
		}
		return validate;
	}

	/**
	 * 验证码类.
	 * 
	 * @author hewenbin
	 * @version v1.0 2018年7月6日 上午11:47:01 hewenbin
	 */
	public static class ImgValidate implements Serializable {
		private static final long serialVersionUID = 1L;
		private String Base64Str; // Base64 值
		private String value; // 验证码值

		public String getBase64Str() {
			return Base64Str;
		}

		public void setBase64Str(String base64Str) {
			Base64Str = base64Str;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Validate:{“" + (Base64Str != null ? "Base64Str\":\"" + Base64Str + "\",\"" : "")
					+ (value != null ? "value\":\"" + value : "") + "\"}  ";
		}

	}

}
