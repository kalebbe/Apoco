/*
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     UserDAO.java
 * Module Version:  1.1
 * Summary:         This class does all of the data handling for user login and registration.
 * 					In the future this will include user account editting, but for now this
 * 					is all that will be included here. I know most of this will change with
 * 					the Spring connectors, but I wanted to get something working for now.
 * 				
 * 					-----UPDATE MILESTONE 3-----
 * 					-Added the update account method which is used to update the user's
 * 					 information when they want.
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-Refactored to use Spring jdbc
 */

package com.gcu.data;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.gcu.model.User;

public class UserDAO implements DataAccessInterface<User> {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemp;

	/*
	 * Sets the data source for the spring jdbc template
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}

	/*
	 * This method creates a new user in the database using a User object
	 * 
	 * UPDATE: per rubric feedback, I will changing the password table to binary because I don't want to
	 * lose points, but I am going to defend me not having binary beforehand. When a password is hashed
	 * before being placed into the database, it removes the need to check for case sensitivity because
	 * the password is first being converted into a hash. A capital "T" will have a different hash value
	 * than a lowercase "t" and it will be stored as such without requiring a binary call. I do not intend
	 * to make any of my other columns binary because I do not see the need to do so. I want users
	 * to be able to login with the username Keberhart or keberhart if they don't feel like capitalizing
	 * that first letter every time. I also think it would be foolish for one user to have the username
	 * Keberhart and another to have the username keberhart. That is my design decision for this project..
	 * the same applies to emails.
	 */
	@Override
	public boolean create(User t) {
		String sql = "INSERT INTO users (EMAIL, USERNAME, FIRST_NAME, LAST_NAME, PASSWORD) VALUES(?,?,?,?,?)";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getEmail(), t.getUsername(), t.getFirstName(), t.getLastName(),
				t.getPassword()) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * This method returns every user in the database as a List. This is currently
	 * not in use, but will likely be used in the future.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> users = jdbcTemp.query(sql, new UserMapper());
		return users;
	}

	/*
	 * This method uses the user's id to return the user model corresponding to said
	 * ID. This method is used to display information for edit account and dispaying
	 * their info.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE ID=?";
		User user = (User) jdbcTemp.queryForObject(sql, new Object[] { id }, new UserMapper());
		return user;
	}

	/*
	 * This method is used to delete the user's account from the database. This is
	 * currently not in use, but will be with milestone 5.
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM users WHERE ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * This method is used to update any bit of the user's information. This was
	 * changed from the ICA to be a bit more flexible without changing every column
	 * in the database.
	 */
	@Override
	public boolean update(User t, int id) {
		String sql = "UPDATE users SET EMAIL = ?, USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?, PASSWORD = ? WHERE ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getEmail(), t.getUsername(), t.getFirstName(), t.getLastName(), t.getPassword(),
				id) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * Checks to see if the user's email address or username is already taken. This
	 * is used when the user registers their account as well as when they try to
	 * update their account in the account editor.
	 */
	public boolean checkUsername(String username) {
		String sql = "SELECT count(*) FROM users WHERE USERNAME =?";
		boolean result = false;
		int count = jdbcTemp.queryForObject(sql, new Object[] { username }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	public boolean checkEmail(String email) {
		String sql = "SELECT count(*) FROM users WHERE EMAIL = ?";
		boolean result = false;
		int count = jdbcTemp.queryForObject(sql, new Object[] { email }, Integer.class);
		if(count > 0) {
			result = true;
		}
		return result;
	}

	/*
	 * This method grabs the user's ID from their login information. This is
	 * required so that I can grab the user's id when they log in.
	 */
	public int getId(String login) {
		try {
			String sql = "SELECT ID FROM users WHERE EMAIL = ? OR USERNAME = ?";
			int id = jdbcTemp.queryForObject(sql, new Object[] { login, login }, Integer.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}
	}
}
