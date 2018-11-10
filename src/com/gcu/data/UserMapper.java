/*
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     UserMapper.java
 * Module Version:  1.0
 * Summary:         This class is used to map the data from the database when creating a new
 * 					user model using Spring JDBC. This was the recommended way to do this
 * 					on TutorialsPoint.
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.User;

/*
 * Quick method for creating and returning a new User object
 */
@SuppressWarnings("rawtypes")
public class UserMapper implements RowMapper {
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("ID"));
		user.setEmail(rs.getString("EMAIL"));
		user.setUsername(rs.getString("USERNAME"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setPassRe(rs.getString("PASSWORD"));
		return user;
	}
}
