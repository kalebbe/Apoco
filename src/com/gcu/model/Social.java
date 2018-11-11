/**
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     Social.java
 * Module Version:  1.01
 * Summary:         This model is used to for the social profile and it's attributes. There isn't much data
 * 					validation here because most of the attributes are derived from dropdwon boxes. This will also
 * 					have a picture in the future
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-Added userId integer + getters and setters for spring jdbc purposes.
 */

package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Social {

	//Temporarily scrapped
	//@NotNull(message = "You must select a picture!")
	//private String picture;

	private boolean privacy; //This determines if randoms can see this user's feed and details

	private String career; //Generic career field

	@NotNull(message = "You must enter a city!")
	@Size(min = 2, max = 40, message = "City must be between 2 and 40 characters!")
	private String city;

	private String State;

	private String status; //Relationship status

	@NotNull(message = "You must enter a biography!")
	@Size(min = 50, max = 5000,  message = "Your biography must be atleast 50 characters!")
	private String bio;

	private String education; //Generic education level

	@NotNull(message = "Please enter your most recent school!")
	@Size(min = 2, max = 100, message = "Your school must be between 2 and 100 characters!")
	private String school; //Actual school

	@NotNull(message = "Please enter your most recent job!")
	@Size(min = 2, max = 100, message = "Your job name must be between 2 and 100 characters!")
	private String job; //Actual job

	private int birthMonth;

	private int birthDay;

	private int birthYear;
	
	private int userId;
	
	private int id;

	/**
	 * Getters and setters below
	 */
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public boolean isPrivacy() {
		return privacy;
	}

	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(int birthMonth) {
		this.birthMonth = birthMonth;
	}

	public int getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(int birthDay) {
		this.birthDay = birthDay;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	/*
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	*/

	/**
	 * Non-default constructor used for creating a Social model from the database.
	 */
	public Social(String bio, boolean privacy, String career, String city, String state, int birthDay, int id,
			int birthMonth, int birthYear, String education, String status, String job, String school, int userId) {
		//this.picture = pic;
		this.id = id;
		this.bio = bio;
		this.privacy = privacy;
		this.career = career;
		this.city = city;
		this.State = state;
		this.birthDay = birthDay;
		this.birthMonth = birthMonth;
		this.birthYear = birthYear;
		this.education = education;
		this.status = status;
		this.job = job;
		this.school = school;
		this.userId = userId;
	}

	/**
	 * Default constructor used to set default values for each property.
	 */
	public Social() {
		//this.picture = "";
		this.bio = "";
		this.privacy = false;
		this.career = "";
		this.city = "";
		this.State = "";
		this.birthDay = 0;
		this.birthMonth = 0;
		this.birthYear = 0;
		this.education = "";
		this.job = "";
		this.status = "";
		this.school = "";
		this.userId = 0;
		this.id = 0;
	}
}
