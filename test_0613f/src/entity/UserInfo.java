package entity;

import java.sql.Date;

public class UserInfo {
	private String id;
	private String pass;
	private String name;
	private Date joinCompany;
	private Integer auth;

	public UserInfo() {

	}

	public UserInfo(String id, String pass, String name, Date joinCompany, Integer auth) {
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.joinCompany = joinCompany;
		this.auth = auth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoinCompany() {
		return joinCompany;
	}

	public void setJoinCompany(Date joinCompany) {
		this.joinCompany = joinCompany;
	}

	public Integer getAuth() {
		return auth;
	}

	public void setAuth(Integer auth) {
		this.auth = auth;
	}
}
