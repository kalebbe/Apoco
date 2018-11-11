/**
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     User.java
 * Module Version:  1.0
 * Summary:         This model holds all of the user fields and completes data validation for
 * 					each field. I will try to add email validation again in the future, but
 * 					it was causing exceptions every time I tried to use it. The email is checked
 * 					in the JavaScript regardless and it doesn't seeem that important because the
 * 					email is easy to spoof anyways.
 */

package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {
	
	//Checks that the email field is not empty
	@NotNull(message="Email cannot be empty!")
	@Size(min=4, max=50, message="Email must be between 4 and 50 characters!")
	private String email;
	
	//Checks the username field is not empty and is 4-30 chars.
	@NotNull(message="Username cannot be empty!")
	@Size(min=4, max=30, message="Username must be between 4 and 30 characters!")
	private String username;
	
	//Checks the first name is not empty and is 2-30 chars.
	@NotNull(message="First Name cannot be empty!")
	@Size(min=2, max=30, message="First Name must be between 2 and 30 characters!")
	private String firstName;
	
	//Checks the last name is not empty and is 2-30 chars.
	@NotNull(message="Last Name cannot be empty!")
	@Size(min=2, max=30, message="Last Name must be between 2 and 30 characters!")
	private String lastName;
	
	//Checks the password is not empty, is > 8 chars, and contains letters and numbers.
	@NotNull(message="Password cannot be empty!")
	@Size(min=8, message="Password must be atleast 8 characters!")
	@Pattern(regexp = "^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$", message="Password must contain letters and numbers!")
	private String password;
	
	//Same as above
	@NotNull(message="Password cannot be empty!")
	@Size(min=8, message="Password must be atleast 8 characters!")
	@Pattern(regexp = "^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$", message="Password must contain letters and numbers!")
	private String passRe;
	
	private int id;

	
	/**
	 * Getters and setters below
	 */
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassRe() {
		return passRe;
	}

	public void setPassRe(String passRe) {
		this.passRe = passRe;
	}
	
	/**
	 * Simple User constructor that sets everything to empty. May include a non-default for this
	 * model in the future if I feel a need for it.
	 */
	public User() {
		this.email = "";
		this.username = "";
		this.firstName = "";
		this.lastName = "";
		this.password = "";
		this.passRe = "";
	}
}
