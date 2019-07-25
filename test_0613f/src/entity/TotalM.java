package entity;

public class TotalM {

	private String id;
	private Integer totalM_id;
	private Integer money;

	private String month;
	private String date;
	private String depature;
	private String destination;

	public TotalM(String id, Integer totalM_id, Integer money, String month, String date, String depature, String destination) {
		this.id = id;
		this.totalM_id = totalM_id;
		this.money = money;
		this.month = month;
		this.date = date;
		this.depature = depature;
		this.destination = destination;
	}

	public TotalM() {

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepature() {
		return depature;
	}

	public void setDepature(String depature) {
		this.depature = depature;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getTotalM_id() {
		return totalM_id;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public void setTotalM_id(Integer totalM_id) {
		this.totalM_id = totalM_id;
	}

}
