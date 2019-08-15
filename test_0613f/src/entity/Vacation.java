package entity;

public class Vacation {
	private Integer vacation_id;
	private String id;
	private Integer year;
	private Integer fromMonth;
	private Integer fromDay;
	private Integer toMonth;
	private Integer toDay;
	private Integer totalDay;
	private String division;
	private String reason;

	public Vacation() {

	}

	public Vacation(Integer vacation_id, String id, Integer year, Integer fromMonth, Integer fromDay, Integer toMonth,
			Integer toDay, Integer totalDay, String division, String reason) {
		super();
		this.vacation_id = vacation_id;
		this.id = id;
		this.year = year;
		this.fromMonth = fromMonth;
		this.fromDay = fromDay;
		this.toMonth = toMonth;
		this.toDay = toDay;
		this.totalDay = totalDay;
		this.division = division;
		this.reason = reason;
	}

	public Integer getVacation_id() {
		return vacation_id;
	}

	public void setVacation_id(Integer vacation_id) {
		this.vacation_id = vacation_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getFromMonth() {
		return fromMonth;
	}

	public void setFromMonth(Integer fromMonth) {
		this.fromMonth = fromMonth;
	}

	public Integer getFromDay() {
		return fromDay;
	}

	public void setFromDay(Integer fromDay) {
		this.fromDay = fromDay;
	}

	public Integer getToMonth() {
		return toMonth;
	}

	public void setToMonth(Integer toMonth) {
		this.toMonth = toMonth;
	}

	public Integer getToDay() {
		return toDay;
	}

	public void setToDay(Integer toDay) {
		this.toDay = toDay;
	}

	public Integer getTotalDay() {
		return totalDay;
	}

	public void setTotalDay(Integer totalDay) {
		this.totalDay = totalDay;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}