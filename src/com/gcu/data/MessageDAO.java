/**
 * This class is used to create new messages, delete messages, and
 * retrieve all of a user's messages from the database with parameters.
 * This will be updated in the future to use more of these methods and 
 * possibly some new ones.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.data;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import com.gcu.model.Message;

public class MessageDAO implements DataAccessInterface<Message> {

	private JdbcTemplate jdbcTemp;
	
	/**
	 * This is setting the dataSource for this dao
	 * @param dataSource The datasource.
	 */
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}
	
	/**
	 * This method gets a message by its id.
	 * @param id This is the id of the message.
	 * @return Message This is the message being got.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Message findById(int id) {
		String sql = "SELECT * FROM messages WHERE ID=?";
		Message message = (Message) jdbcTemp.queryForObject(sql, new Object[] { id }, new MessageMapper());
		return message;
	}

	/**
	 * This method gets every message from the database. Will likely never be used. May
	 * even be removed and return null for privacy protection.
	 * @return List<Message> The list of messages.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Message> findAll() {
		String sql = "SELECT * FROM messages";
		List<Message> messages = jdbcTemp.query(sql, new MessageMapper());
		return messages;
	}
	
	/**
	 * This method gets the inbox of a certain user. Refined by type.
	 * @param id The id of the user logged in.
	 * @param type The type of messages being grabbed.
	 * @return List<Message> The returned messages.
	 */
	@SuppressWarnings("unchecked")
	public List<Message> getMessages(int id, String type){
		String sql = "SELECT * FROM messages WHERE RECEIVER_ID = ? AND TYPE = ?";
		//PreparedStatementSetter for multiple parameters.
		List<Message> message = jdbcTemp.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException{
				ps.setInt(1, id);
				ps.setString(2, type);
			}
		}, new MessageMapper()); //Maps messages to their values.
		return message;
	}

	/**
	 * This method is used to create a new message in the database. Currently
	 * used only for requests.
	 * @param t The message object.
	 * @return boolean Whether or not the object creation was successful.
	 */
	@Override
	public boolean create(Message t) {
		String sql = "INSERT INTO messages (SENDER_ID, RECEIVER_ID, BODY, TYPE) VALUES(?,?,?,?)";
		if(jdbcTemp.update(sql, t.getSenderId(), t.getReceiverId(), t.getBody(), t.getType()) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to update a message in the database. Currently not in
	 * use.
	 * @param t The message being updated.
	 * @return boolean Whether or not the object update was successful.
	 */
	@Override
	public boolean update(Message t) {
		String sql = "UPDATE messages SET SENDER_ID = ?, RECEIVER_ID = ?, BODY = ?, TYPE = ? WHERE ID = ?";
		if(jdbcTemp.update(sql, t.getSenderId(), t.getReceiverId(), t.getBody(), t.getType(), t.getId()) == 1) {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to delete a message in the database. Currently only used
	 * to delete requests.
	 * @param id The id of the message.
	 * @return boolean Whether or not the object deletion was successful.
	 */
	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM messages WHERE ID = ?";
		if(jdbcTemp.update(sql, id) == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method is used to check if a user has any friend requests. May be reworked
	 * in the future to return the count so user's can see how many bots.. er friends, they
	 * have waiting acceptance. We'll see.
	 * @param senderId The id of the request sender.
	 * @param receiverId The id of this dude.
	 * @return boolean Whether or not they have any friend requests.
	 */
	public boolean checkRequest(int senderId, int receiverId) {
		String sql;
		int count;
		if(senderId == 0) { //This is used to check if a user has any requests at all
			sql = "SELECT count(*) FROM messages WHERE RECEIVER_ID=?";
			count = jdbcTemp.queryForObject(sql, new Object[] {receiverId}, Integer.class);
		}
		else { //This is used to check if a user has sent a request to a specific user.
			sql = "SELECT count(*) FROM messages WHERE SENDER_ID=? AND RECEIVER_ID=?";
			count = jdbcTemp.queryForObject(sql, new Object[] {senderId, receiverId}, Integer.class);
		}
		if(count > 0) {
			return true;
		}
		return false;
	}
}
