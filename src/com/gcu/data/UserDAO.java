/**
 * This class does all of the data handling for user login and registration.
 * In the future this will include user account editting, but for now this
 * is all that will be included here. I know most of this will change with
 * the Spring connectors, but I wanted to get something working for now.
 * 				
 * -----UPDATE MILESTONE 3-----
 * -Added the update account method which is used to update the user's
 * information when they want.
 * 
 * -----UPDATE MILESTONE 4-----
 * -Refactored to use Spring jdbc
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.1
 * @since   2018-11-25
 */

package com.gcu.data;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.gcu.model.User;

public class UserDAO implements DataAccessInterface<User> {

	@SuppressWarnings("unused")
	private DataSource dataSource; //Changed to private per rubric feedback
	private JdbcTemplate jdbcTemp;

	/**
	 * Sets the data source for the spring jdbc template.
	 * @param dataSource.
	 * @return Nothing.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}

	/**
	 * This method creates a new user in the database using a User object.
	 * @param t This is the user created in the database.
	 * @return boolean This is the success or failure of the query.
	 */
	@Override
	public boolean create(User t) {
		String sql = "INSERT INTO users (EMAIL, USERNAME, FIRST_NAME, LAST_NAME, PASSWORD) VALUES(?,?,?,?,?)";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getEmail(), t.getUsername(), t.getFirstName(), t.getLastName(),
				t.getPassword()) == 1) { //if update returns 1, then 1 row was affected, so the update worked.
			result = true;
		}
		return result;
	}

	/**
	 * This method returns every user in the database as a List. This is currently
	 * not in use, but will likely be used in the future for Admin purposes.
	 * @return List<User> This is the list of users returned by this method.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		List<User> users = jdbcTemp.query(sql, new UserMapper()); //User mapper maps db data to model
		return users;
	}

	/**
	 * This method uses the user's id to return the user model corresponding to said
	 * ID. This method is used to display information for edit account and displaying
	 * their info.
	 * @param id This is the id of the user being pulled.
	 * @return User This is the user object returned.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE ID=?";
		User user = (User) jdbcTemp.queryForObject(sql, new Object[] { id }, new UserMapper());
		return user;
	}

	/**
	 * This method is used to delete the user's account from the database. This is
	 * currently not in use, but will be coming in a future update.
	 * @param id This is the id of the user being deleted.
	 * @return boolean This is the success or failure of the deletion.
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

	/**
	 * This method is used to update any bit of the user's information. This was
	 * changed from the ICA to be a bit more flexible without changing every column
	 * in the database.
	 * @param t This is the user being updated.
	 * @return boolean This is the success or failure of the update.
	 */
	@Override
	public boolean update(User t) {
		String sql = "UPDATE users SET EMAIL = ?, USERNAME = ?, FIRST_NAME = ?, LAST_NAME = ?, PASSWORD = ? WHERE ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getEmail(), t.getUsername(), t.getFirstName(), t.getLastName(), t.getPassword(),
				t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks to see if the user's username is already taken. This is used when the
	 * user registers or when they try to update their username.
	 * @param username This is the username being checked for duplicity.
	 * @return boolean This is whether or not the username is taken.
	 */
	public boolean checkUsername(String username) {
		String sql = "SELECT count(*) FROM users WHERE USERNAME =?";
		boolean result = false;
		
		//Query returns an int with the number of rows that contain this username
		int count = jdbcTemp.queryForObject(sql, new Object[] { username }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * This method checks to see if the user's email is already taken. This used to be
	 * merged with the checkUsername method, but was separated for milestone 5.
	 * @param email This is the email being checked for duplicity.
	 * @return boolean This is whether or not the email is taken.
	 */
	public boolean checkEmail(String email) {
		String sql = "SELECT count(*) FROM users WHERE EMAIL = ?";
		boolean result = false;
		
		//Query returns an int with the number of rows that contain this email
		int count = jdbcTemp.queryForObject(sql, new Object[] { email }, Integer.class);
		if(count > 0) {
			result = true;
		}
		return result;
	}

	/**
	 * This method grabs the user's ID from their login information. This is
	 * required so that I can grab the user's id when they log in.
	 * @param login This is the username or email being used to grab id.
	 * @return int This is the id returned from the database or 0 if non-existent.
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
