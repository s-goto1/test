package entity;

public class TotalM {

	private String id;
	private Integer totalM_id;
	private Integer money;

	private Integer year;
	private Integer month;
	private Integer day;
	private String depature;
	private String destination;
	private String division;
	private String place;
	private String purpose;
	private String transpotation;

	public TotalM(String id, Integer totalM_id, Integer money,
			Integer year,Integer month, Integer day,String transpotation, String depature,
			String destination, String division, String place, String purpose) {
		this.id = id;
		this.totalM_id = totalM_id;
		this.year = year;
		this.money = money;
		this.month = month;
		this.day = day;
		this.transpotation = transpotation;
		this.depature = depature;
		this.destination = destination;
		this.division = division;
		this.place = place;
		this.purpose = purpose;
	}

	public TotalM() {

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

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTranspotation() {
		return transpotation;
	}

	public void setTranspotation(String transpotation) {
		this.transpotation = transpotation;
	}

}
