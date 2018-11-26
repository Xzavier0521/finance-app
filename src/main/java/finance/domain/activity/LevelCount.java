package finance.domain.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: LevelCount.java, v0.1 2018/10/6 2:19 AM lili Exp $
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LevelCount implements Serializable {

	private static final long serialVersionUID = 4759940242508564982L;
	private int allLevelCount;
	private int firstLevelCount;
	private int secondLevelCount;
}
