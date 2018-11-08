/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     UserBusinessInterface.java
 * Module Version:  1.0
 * Summary:         This interface gives the methods required in the UserBusinessService class and any
 * 				    other classes that may eventually implement this interafce
 */
package com.gcu.business;

import com.gcu.model.User;

public interface UserBusinessInterface {

	public int create(User t);
	public int login(String login, String password);
	public boolean changePass(User t, int id);
	public boolean checkPass(String pass, int id);
	public boolean updateFirst(User t, int id);
	public boolean updateLast(User t, int id);
	public boolean updateUser(User t, int id);
	public boolean checkUser(User t);
	public boolean checkEmail(User t);
	public boolean updateEmail(User t, int id);
	public User findById(int id);
	
}
