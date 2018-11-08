/*
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.1
 * Module Name:     UserBusinessService.java
 * Module Version:  1.1
 * Summary:         This class handles the business side of user login and registration. It also uses the
 * 				    BCrypt class to hash passwords and check the hash upon login.
 * 					
 * 					-----UPDATES MILESTONE 3-----
 * 					-BCrypt is now imported via jar file
 * 				    -updateAccount method can be used to change any user property in the database minus password.
 * 					-changePass can now be used to change the user's password as long as they know their old
 * 					 password.
 * 					-Implemented UserBusinessInterface for Dependency Injection purposes.
 * 			
 * 					-----UPDATES MILESTONE 4-----
 * 					-Dependency injeciton added for DAO to be autowired.
 * 					-Class restructured for Spring JDBC.
 */

package com.gcu.business;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.UserDAO;
import com.gcu.model.User;

public class UserBusinessService implements UserBusinessInterface {
	@Autowired
	private UserDAO dao;

	/*
	 * This method checks to make sure the username and email aren't taken, the
	 * passwords aren't equal, and then encrypts the password using BCrypt.
	 */
	@Override
	public int create(User t) {
		String hashPass = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
		t.setPassword(hashPass);
		if(dao.create(t)) {
			return dao.getId(t.getEmail());
		}
		else return 0;
	}

	/*
	 * This method grabs the hashed database password from the UserDAO class and
	 * then uses BCrypt to check the hashed password with the user input password.
	 * If the username(or email) and password matches, the getUsername method is
	 * called and the user is logged in. The getUsername method is necessary because
	 * the user can log in with email or username.
	 */
	@Override
	public int login(String login, String password) {
		int id = dao.getId(login);
		if (id == 0) {
			return id;
		}

		if (checkPass(password, id)) {
			return id;
		} else {
			return -1;
		}
	}

	/*
	 * This method calls the UserDAO to update the user's password as long as they
	 * pass the required checks. User's password must have a letter and number as
	 * well as being 8 characters long. This method also uses BCrypt to match the
	 * user's input to the currently hashed password in the database.
	 */
	@Override
	public boolean changePass(User t, int id) {
		if (t.getPassword().length() < 8
				|| !t.getPassword().matches("^([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*$")) {
			return false;
		}
		String hashPass = BCrypt.hashpw(t.getPassword(), BCrypt.gensalt());
		t.setPassword(hashPass);
		if (dao.update(t, id)) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean checkPass(String pass, int id) {
		User user = dao.findById(id);

		if (BCrypt.checkpw(pass, user.getPassword())) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean updateFirst(User t, int id) {
		if (t.getFirstName().length() < 2 || t.getFirstName().length() > 30) {
			return false;
		} else {
			if (dao.update(t, id)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean updateLast(User t, int id) {
		if (t.getLastName().length() < 2 || t.getLastName().length() > 30) {
			return false;
		} else {
			if (dao.update(t, id)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean updateUser(User t, int id) {
		if (t.getUsername().length() < 4 || t.getUsername().length() > 30) {
			return false;
		} else {
			if (dao.update(t, id)) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public boolean checkUser(User t) {
		return dao.checkUsername(t.getUsername());
	}

	@Override
	public boolean checkEmail(User t) {
		return dao.checkEmail(t.getEmail());
	}

	@Override
	public boolean updateEmail(User t, int id) {
		try {
			InternetAddress email = new InternetAddress(t.getEmail());
			email.validate();
			if (dao.update(t, id)) {
				return true;
			} else
				return false;
		} catch (AddressException e) {
			return false;
		}

	}

	/*
	 * Returns a user model through the use of their ID. This is now used for the
	 * account editor rather than saving all information to the session.
	 */
	@Override
	public User findById(int id) {
		return dao.findById(id);
	}
}