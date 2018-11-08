/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.1
 * Module Name:     Feed.java
 * Module Version:  1.0
 * Summary:         This model is used for user feed posts. This is pretty simple and
 * 					there isn't much data validation here either. In the future, this will
 * 					have pictures as well.
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-Added userId + getter and setter for spring jdbc purposes
 */
package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Feed {
	@NotNull(message = "You must choose a privacy option!")
	private String privacy;
	
	@NotNull(message = "Your post cannot be null!")
	@Size(min = 20, max = 5000, message="Your post must have more than 20 characters!")
	private String feed;
	
	private String name;
	
	private String link;
	
	private int userId;
	
	private int id;
	
	/*
	 * Here be getters and setters
	 */
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	
	/*
	 * Default constructor to give default values to the model
	 */
	public Feed() {
		this.link = "";
		this.privacy = "";
		this.feed = "";
		this.name = "";
		this.id = 0;
		this.userId = 0;
	}
	
	/*
	 * Non-standard constructor used to set model manually from database.
	 * Done to allow for reading data from database and displaying in view.
	 */
	public Feed(int id, int userId, String feed, String privacy, String name, String link) {
		this.id = id;
		this.feed = feed;
		this.privacy = privacy;
		this.name = name;
		this.link = link;
		this.userId = userId;
	}
}
