package finance.domain.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: CoinLockParamDto.java, v0.1 2018/11/14 3:41 PM lili Exp $
 */
@Data
public class CoinLockParamDto implements Serializable {
    private static final long serialVersionUID = -1552867423495298937L;
    private Long              userId;
    private Long              giftId;
    private int               coinNum;

}
