/*
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     FeedDAO.java
 * Module Version:  1.0
 * Summary:         This class is used for any database interactions with Feed objects. This was
 * 					separated from SocialDAO because object oriented programming and all that.
 */

package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.gcu.model.Feed;

public class FeedDAO implements DataAccessInterface<Feed> {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemp;

	/*
	 * Sets the data source for this class and instantiates the spring jdbc template
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}

	/*
	 * This method is used to return a socialfeed object by its ID. This is
	 * currently not in use, but will likely be soon.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Feed findById(int id) {
		String sql = "SELECT * FROM socialfeed WHERE ID=?";
		Feed feed = (Feed) jdbcTemp.queryForObject(sql, new Object[] { id }, new FeedMapper());
		return feed;
	}

	/*
	 * This method returns all feed posts in the database and will likely be used in
	 * the future for admin/mod powers, but is currently not in use.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Feed> findAll() {
		String sql = "SELECT * FROM socialfeed";
		try {
			List<Feed> feed = jdbcTemp.query(sql, new FeedMapper());
			return feed;
		} catch (Exception e) {
			e.printStackTrace();
			// throw new DatabaseException(e);
			return null;
		}
	}

	/*
	 * This method is used to find get all of the feed corresponding to a user id.
	 */
	@SuppressWarnings("unchecked")
	public List<Feed> findUserFeed(int id) {
		String sql = "SELECT * FROM socialfeed WHERE USER_ID=?";
		// PreparedStatementSetter required for multiple objects with a parameter
		List<Feed> feed = jdbcTemp.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}

		}, new FeedMapper());
		return feed;
	}

	/*
	 * This method is used to insert a new Feed object into the database
	 */
	@Override
	public boolean create(Feed t) {
		String sql = "INSERT INTO socialfeed (USER_ID, NAME, POST, PRIVACY, LINK) VALUES (?,?,?,?,?)";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getUserId(), t.getName(), t.getFeed(), t.getPrivacy(), t.getLink()) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * This method is used to update an existing feed object in the database. This
	 * is currently not in use, but may be used in the future.
	 */
	@Override
	public boolean update(Feed t, int id) {
		String sql = "UPDATE socialfeed SET USER_ID = ?, NAME = ?, POST = ?, PRIVACY = ?, LINK = ? WHERE ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getUserId(), t.getName(), t.getFeed(), t.getPrivacy(), t.getLink(), id) == 1) {
			result = true;
		}
		return result;
	}

	/*
	 * This method is used to delete the feed object from database using the ID of
	 * the object
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM socialfeed WHERE ID = ? ";
		boolean result = false;
		if (jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}

}