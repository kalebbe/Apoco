package com.gcu.model;

import java.util.Date;

public class Business {
	private int userId;
	private int id;
	private Date dob;
	private String profession;
	private String gender;
	private String ethnicity;
	private String city;
	private String state;
	private String education;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	
	public Business(int userId, int id, Date dob, String profession, String gender, String ethnicity, String city,
			String state, String education) {
		this.userId = userId;
		this.id = id;
		this.dob = dob;
		this.profession = profession;
		this.gender = gender;
		this.ethnicity = ethnicity;
		this.city = city;
		this.state = state;
		this.education = education;
	}
	
	public Business() {
		this.userId = 0;
		this.id = 0;
		this.dob = null;
		this.profession = "";
		this.gender = "";
		this.ethnicity = "";
		this.city = "";
		this.state = "";
		this.education = "";
	}
}
