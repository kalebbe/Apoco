/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     SocialDAO.java
 * Module Version:  1.1
 * Summary:         This class is used for all things involving our social service and the database. Currently this class
 * 					can create the user's social profile, create a feed post, and grab the data as well. In the future,
 * 					this class will allow the user to update their profile (and possibly feed posts) as well as 
 * 					post comments on feed posts.
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-Updated to comply with Spring jdbc.
 */

package com.gcu.data;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import com.gcu.model.Social;

public class SocialDAO implements DataAccessInterface<Social> {

	@SuppressWarnings("unused")
	private DataSource dataSource; // Datasource grabbed from appconfig
	private JdbcTemplate jdbcTemp; // Used for spring jdbc database calls

	/*
	 * This method sets the data source for my database calls and instantiates the
	 * template
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}

	/*
	 * This method takes the user's id and returns the user model for them. If there
	 * is no result for that id, the method will catch the exception and return
	 * null.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Social findById(int id) {
		try {
			String sql = "SELECT * FROM socialprofiles WHERE USER_ID=?";
			Social social = (Social) jdbcTemp.queryForObject(sql, new Object[] { id }, new SocialMapper());
			return social;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/*
	 * This method returns every single social profile in the database. As of right
	 * now, this method does not have a purpose, but may be used in the future for
	 * data purposes.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Social> findAll() {
		String sql = "SELECT * FROM socialprofiles";
		List<Social> social = jdbcTemp.query(sql, new SocialMapper());
		return social;
	}

	/*
	 * This method takes the Social model and inserts it into the database.
	 */
	@Override
	public boolean create(Social t) {
		String sql = "INSERT INTO socialprofiles (USER_ID, CAREER, CITY, STATE, RELATIONSHIP, BIO,"
				+ " EDUCATION, SCHOOL, JOB, BIRTH_DATE, PRIVACY) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		boolean result = false;

		// Makes a simple String date with / between numbers
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + "/" + t.getBirthYear();

		// This checks to see if the insertion into the database returns 1 as a result
		// which would mean the insertion was successful.
		if (jdbcTemp.update(sql, t.getUserId(), t.getCareer(), t.getCity(), t.getState(), t.getStatus(), t.getBio(),
				t.getEducation(), t.getSchool(), t.getJob(), date, t.isPrivacy()) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * This method updates a single column of the social profile. This was changed
	 * from the base ICA update method which would update the entire model. I feel
	 * like this is more efficient as only the required columns are updated. This is
	 * currently not in use.
	 */
	@Override
	public boolean update(Social t) {
		String sql = "UPDATE socialprofiles SET CAREER = ?, CITY = ?, STATE = ?, RELATIONSHIP = ?, BIO = ?, EDUCATION = ?, "
				+ "SCHOOL = ?, JOB = ?, BIRTH_DATE = ?, PRIVACY = ? WHERE ID = ?";
		boolean result = false;
		String date = t.getBirthMonth() + "/" + t.getBirthDay() + t.getBirthYear();
		if (jdbcTemp.update(sql, t.getCareer(), t.getCity(), t.getState(), t.getStatus(), t.getBio(), t.getEducation(),
				t.getSchool(), t.getJob(), date, t.isPrivacy(), t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * This method deletes the social profile of the logged in user. Currently not
	 * in use.
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM socialprofiles WHERE USER_ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}
}
