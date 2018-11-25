/**
 * This class is used for any database interactions with Feed objects. This was
 * separated from SocialDAO because object oriented programming and all that.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-11-25
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

	/**
	 * Sets the data source for this class and instantiates the spring jdbc template.
	 * @param dataSource.
	 * @return Nothing.
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}

	/**
	 * This method is used to return a socialfeed object by its ID. This is
	 * currently not in use, but will likely be soon.
	 * @param id Used to find a feed object by its ID.
	 * @return Feed This returns the feed object grabbed from DB.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Feed findById(int id) {
		String sql = "SELECT * FROM socialfeed WHERE ID=?";
		Feed feed = (Feed) jdbcTemp.queryForObject(sql, new Object[] { id }, new FeedMapper());
		return feed;
	}

	/**
	 * This method returns all feed posts in the database and will likely be used in
	 * the future for admin/mod powers, but is currently not in use.
	 * @return List<Feed> This returns all of the feed items.
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

	/**
	 * This method is used to find get all of the feed corresponding to a user id.
	 * @param id This is the user's id to grab feed from.
	 * @return List<Feed> This returns all of the feed correlated to the id.
	 */
	@SuppressWarnings("unchecked")
	public List<Feed> findUserFeed(int id) {
		String sql = "SELECT * FROM socialfeed WHERE USER_ID=? ORDER BY DATE_POSTED DESC";
		// PreparedStatementSetter required for multiple objects with a parameter
		List<Feed> feed = jdbcTemp.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, id);
			}

		}, new FeedMapper());
		return feed;
	}

	/**
	 * This method is used to insert a new Feed object into the database.
	 * @param t This is the feed object being created.
	 * @return boolean This returns true if the feed is created successfully.
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

	/**
	 * This method is used to update an existing feed object in the database. This
	 * method is now used in milestone 5 to update the POST column. May be updated
	 * in the future for an upvote/downvote update.
	 * @param t This is the feed object being updated.
	 * @return boolean This returns true if the feed is updated successfully.
	 */
	@Override
	public boolean update(Feed t) {
		String sql = "UPDATE socialfeed SET USER_ID = ?, NAME = ?, POST = ?, PRIVACY = ?, LINK = ? WHERE ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, t.getUserId(), t.getName(), t.getFeed(), t.getPrivacy(), t.getLink(), t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	/**
	 * This method is used to delete the feed object from database using the ID of
	 * the object.
	 * @param id This is the id of the feed being deleted.
	 * @return boolean This returns true if the feed is deleted successfully.
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