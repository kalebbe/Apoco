/*
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     SocialMapper.java
 * Module Version:  1.0
 * Summary:         This class is used to map the data from the database when creating a new
 * 					Social model using Spring JDBC. This was the recommended way to do this
 * 					on TutorialsPoint.
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Social;


@SuppressWarnings("rawtypes")
public class SocialMapper implements RowMapper {

	/*
	 * Quick method for creating and returning a new social object from the database.
	 */
	@Override
	public Social mapRow(ResultSet rs, int rowNum) throws SQLException {
		Social social = new Social();
		String date = rs.getString("BIRTH_DATE");
		String arr[] = date.split("/");
		
		social.setBio(rs.getString("BIO"));
		social.setBirthDay(Integer.parseInt(arr[1]));
		social.setBirthMonth(Integer.parseInt(arr[0]));
		social.setBirthYear(Integer.parseInt(arr[2]));
		social.setCareer(rs.getString("CAREER"));
		social.setCity(rs.getString("CITY"));
		social.setEducation(rs.getString("EDUCATION"));
		social.setJob(rs.getString("JOB"));
		social.setPrivacy(rs.getBoolean("PRIVACY"));
		social.setSchool(rs.getString("SCHOOL"));
		social.setState(rs.getString("STATE"));
		social.setStatus(rs.getString("RELATIONSHIP"));
		social.setUserId(rs.getInt("USER_ID"));
		return social;
	}
}