package cn.zhishush.finance.ext.api.model;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili-5c
 * @version $Id: Scene.java, v0.1 2018/10/29 3:34 PM lili Exp $
 */
@Data
@Builder
public class Scene implements Serializable {

	/**
	 * 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64
	 */
	private String scene_str;
}
