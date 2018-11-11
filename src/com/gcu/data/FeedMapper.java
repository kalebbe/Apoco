/**
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     FeedMapper.java
 * Module Version:  1.0
 * Summary:         This class is used to map the data from the database when creating a new
 * 					feed model using Spring JDBC. This was the recommended way to do this
 * 					on TutorialsPoint.
 */

package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.gcu.model.Feed;


@SuppressWarnings("rawtypes")
public class FeedMapper implements RowMapper {

	/**
	 * Quick method for creating and returning a new Feed object from the database.
	 */
	@Override
	public Feed mapRow(ResultSet rs, int rowNum) throws SQLException {
		Feed feed = new Feed();
		feed.setFeed(rs.getString("POST"));
		feed.setId(rs.getInt("ID"));
		feed.setUserId(rs.getInt("USER_ID"));
		feed.setLink(rs.getString("LINK"));
		feed.setName(rs.getString("NAME"));
		feed.setPrivacy(rs.getString("PRIVACY"));
		return feed;
	}

}
