package finance.api.model.vo;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: finance-server
 *
 * @description: 我的战绩VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-22 11:50
 **/
public class MyRecordVO {
    private Integer totalOutCoin;
    private Integer totalInCoin;
    private Integer totalSign;
    private List<MyCoinGameLogVO> records = new ArrayList();

    public Integer getTotalOutCoin() {
        return totalOutCoin;
    }

    public void setTotalOutCoin(Integer totalOutCoin) {
        this.totalOutCoin = totalOutCoin;
    }

    public Integer getTotalInCoin() {
        return totalInCoin;
    }

    public void setTotalInCoin(Integer totalInCoin) {
        this.totalInCoin = totalInCoin;
    }

    public Integer getTotalSign() {
        return totalSign;
    }

    public void setTotalSign(Integer totalSign) {
        this.totalSign = totalSign;
    }

    public List<MyCoinGameLogVO> getRecords() {
        return records;
    }

    public void setRecords(List<MyCoinGameLogVO> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "MyRecordVO{" +
                "totalOutCoin=" + totalOutCoin +
                ", totalInCoin=" + totalInCoin +
                ", totalSign=" + totalSign +
                ", records=" + records +
                '}';
    }
}
