package finance.api.model.vo.coin;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-22 13:48
 **/
public class MyCoinGameLogVO {
	private String datetime;
	private String coinNum;
	private String desc;
	private String type;

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getCoinNum() {
		return coinNum;
	}

	public void setCoinNum(Integer coinNum) {
		if (coinNum.compareTo(0) >= 0) {
			this.coinNum = "+" + coinNum.toString();
		} else {
			this.coinNum = coinNum.toString();
		}
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getType() {
		return type;
	}

	public void setType(Integer type) {
		if (type.compareTo(0) >= 0) {
			this.type = "in";
		} else {
			this.type = "out";
		}
	}

	@Override
	public String toString() {
		return "MyCoinGameLogVO{" + "datetime='" + datetime + '\'' + ", coinNum='" + coinNum + '\'' + ", desc='" + desc
				+ '\'' + ", type='" + type + '\'' + '}';
	}
}
