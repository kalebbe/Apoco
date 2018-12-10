/**
 * This class is used to create new friends, delete friends, and
 * retrieve all of a user's friends from the database. This does
 * not implement the DataAccessInterface because there is only one
 * method that I use verbatim from that interface.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-09
 */

package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.gcu.model.Friend;

public class FriendDAO{

	private JdbcTemplate jdbcTemp;
	
	/**
	 * This is just setting the datasource for this dao
	 * @param dataSource Self Explanatory.
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}
	
	/**
	 * This method grabs all of the friends for a specific user.
	 * @param id This is the id of the user.
	 * @return List<Friend> This is the list of friends returned.
	 */
	@SuppressWarnings("unchecked")
	public List<Friend> findAll(int id) {
		String sql = "SELECT * FROM friends WHERE USER_ID=?";
			List<Friend> friend = jdbcTemp.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException{
					ps.setInt(1, id);
				}
			}, new FriendMapper());
			return friend;
	}

	/**
	 * This method creates a new Friend in the database.
	 * @param t This is the Friend being created.
	 * @return boolean This is the success or failure of the operation.
	 */
	public boolean create(Friend t) {
		String sql = "INSERT INTO friends (USER_ID, FRIEND_ID) VALUES(?,?)";
		boolean result = false;
		if(jdbcTemp.update(sql, t.getUserId(), t.getFriendId()) == 1) {
			result = true;
		}
		return result;
	}
	
	/**
	 * This method deletes a Friend in the database.
	 * @param t This is the friend being deleted.
	 * @return boolean This is the success or failure of the operation.
	 */
	public boolean delete(Friend t) {
		String sql = "DELETE FROM friends WHERE USER_ID=? AND FRIEND_ID=?";
		boolean result = false;
		if(jdbcTemp.update(sql, t.getUserId(), t.getFriendId()) == 1) {
			result = true;
		}
		return result;
	}
}
