package entity;

import java.sql.Time;

public class TimeTable {
	private Integer timeTableId;
	private String id;
	private Integer companyNum;
	private String visitName;
	private Time visitComeTime;
	private Time visitLeaveTime;
	private Time visitBrakeTime;
	private Integer roundTime;
	private Double vacationDay;

	public TimeTable() {

	}

	public TimeTable(Integer timeTableId, String id, Integer companyNum, String visitName, Time visitComeTime,
			Time visitLeaveTime, Time visitBrakeTime, Integer roundTime, Double vacationDay) {
		this.timeTableId = timeTableId;
		this.id = id;
		this.companyNum = companyNum;
		this.visitName = visitName;
		this.visitComeTime = visitComeTime;
		this.visitLeaveTime = visitLeaveTime;
		this.visitBrakeTime = visitBrakeTime;
		this.roundTime = roundTime;
		this.vacationDay = vacationDay;
	}

	public Integer getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(Integer timeTableId) {
		this.timeTableId = timeTableId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getCompanyNum() {
		return companyNum;
	}

	public void setCompanyNum(Integer companyNum) {
		this.companyNum = companyNum;
	}

	public String getVisitName() {
		return visitName;
	}

	public void setVisitName(String visitName) {
		this.visitName = visitName;
	}

	public Time getVisitComeTime() {
		return visitComeTime;
	}

	public void setVisitComeTime(Time visitComeTime) {
		this.visitComeTime = visitComeTime;
	}

	public Time getVisitLeaveTime() {
		return visitLeaveTime;
	}

	public void setVisitLeaveTime(Time visitLeaveTime) {
		this.visitLeaveTime = visitLeaveTime;
	}

	public Time getVisitBrakeTime() {
		return visitBrakeTime;
	}

	public void setVisitBrakeTime(Time visitBrakeTime) {
		this.visitBrakeTime = visitBrakeTime;
	}

	public Integer getRoundTime() {
		return roundTime;
	}

	public void setRoundTime(Integer roundTime) {
		this.roundTime = roundTime;
	}

	public Double getVacationDay() {
		return vacationDay;
	}

	public void setVacationDay(Double vacationDay) {
		this.vacationDay = vacationDay;
	}
}
