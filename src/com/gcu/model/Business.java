package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Business {
	private int userId;
	private int id;
	private int birthDay;
	private int birthMonth;
	private int birthYear;
	private String profession;
	private String gender;
	private String ethnicity;
	
	@NotNull(message="You must enter a city!")
	@Size(min = 2, max = 40, message = "City must be between 2 and 40 characters!")
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
	
	public int getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}
	public int getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
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
	
	public Business(int userId, int id, int birthDay, int birthMonth, int birthYear, String profession, String gender, String ethnicity, String city,
			String state, String education) {
		this.userId = userId;
		this.id = id;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
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
		this.birthDay = 0;
		this.birthMonth = 0;
		this.birthYear = 0;
		this.profession = "";
		this.gender = "Male";
		this.ethnicity = "";
		this.city = "";
		this.state = "";
		this.education = "";
	}
}
