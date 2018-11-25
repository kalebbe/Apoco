/**
 * This model holds all of the user fields and completes data validation for
 * each field. I will try to add email validation again in the future, but
 * it was causing exceptions every time I tried to use it. The email is checked
 * in the JavaScript regardless and it doesn't seeem that important because the
 * email is easy to spoof anyways.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-11-25
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
	 * Getter for the id variable.
	 * @return int This is the id of the user.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter for the id variable.
	 * @param id This is the new id of the user.
	 * @return Nothing.
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Getter for the email variable.
	 * @return String This is the email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for the email variable.
	 * @param email This is the new email of the user.
	 * @return Nothing.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for the username variable.
	 * @return String This is the username of the user.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter for the username variable.
	 * @param username This is the new username of the user.
	 * @return Nothing.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter for the firstName variable.
	 * @return String This is the first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for the firstName variable.
	 * @param firstName This is the new first name of the user.
	 * @return Nothing.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for the lastName variable.
	 * @return String This is the last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for the lastName variable.
	 * @param lastName This is the new last name of the user.
	 * @return Nothing.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for the password variable.
	 * @return String This is the password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for the password variable.
	 * @param password This is the new password of the user.
	 * @return Nothing.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for the passRe variable.
	 * @return String this is the repeated password of the user.
	 */
	public String getPassRe() {
		return passRe;
	}

	/**
	 * Setter for the passRe variable.
	 * @param passRe This is the new repeated password of the user.
	 * @return Nothing.
	 */
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
