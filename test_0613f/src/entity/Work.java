package entity;

import java.sql.Time;

public class Work {
	private Integer work_id;
	private String id;
	private Integer year;
	private Integer month;
	private Integer day;
	private Time comeTime;
	private Time leaveTime;
	private Time brakeTime;
	private Time workTime;
	private Time overTime;
	private Integer visit;
	private String notes;
	private String vacation;

	public Work() {

	}

	public Work(Integer work_id, String id, Integer year, Integer month, Integer day, Time comeTime,
			Time leaveTime, Time brakeTime, Time workTime, Time overTime, Integer visit, String notes,
			String vacation) {
		super();
		this.work_id = work_id;
		this.id = id;
		this.year = year;
		this.month = month;
		this.day = day;
		this.comeTime = comeTime;
		this.leaveTime = leaveTime;
		this.brakeTime = brakeTime;
		this.workTime = workTime;
		this.overTime = overTime;
		this.visit = visit;
		this.notes = notes;
		this.vacation = vacation;
	}

	public Integer getWork_id() {
		return work_id;
	}

	public void setWork_id(Integer work_id) {
		this.work_id = work_id;
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

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Time getComeTime() {
		return comeTime;
	}

	public void setComeTime(Time comeTime) {
		this.comeTime = comeTime;
	}

	public Time getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Time leaveTime) {
		this.leaveTime = leaveTime;
	}

	public Time getBrakeTime() {
		return brakeTime;
	}

	public void setBrakeTime(Time brakeTime) {
		this.brakeTime = brakeTime;
	}

	public Time getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Time workTime) {
		this.workTime = workTime;
	}

	public Time getOverTime() {
		return overTime;
	}

	public void setOverTime(Time overTime) {
		this.overTime = overTime;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getVacation() {
		return vacation;
	}

	public void setVacation(String vacation) {
		this.vacation = vacation;
	}
}
