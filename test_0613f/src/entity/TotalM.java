package entity;

public class TotalM {

	private String id;
	private Integer totalM_id;
	private Integer money;

	private Integer month;
	private Integer date;
	private String depature;
	private String destination;
	private String division;

	public TotalM(String id, Integer totalM_id, Integer money,
			Integer month, Integer date, String depature,
			String destination, String division) {
		this.id = id;
		this.totalM_id = totalM_id;
		this.money = money;
		this.month = month;
		this.date = date;
		this.depature = depature;
		this.destination = destination;
		this.division = division;
	}

	public TotalM() {

	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
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
