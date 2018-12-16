/**
 * This interface gives the methods required in the MessageBusinessService class and any
 * other classes that may eventually implement this interface.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.business;

import java.util.List;

import com.gcu.model.Message;

public interface MessageBusinessInterface {

	/**
	 * Creates a new message.
	 * @param t The actual message object.
	 * @return boolean Whether or not it was created.
	 */
	public boolean create(Message t);

	/**
	 * Deletes any message by id.
	 * @param id The message's id.
	 * @return boolean Whether or not it was deleted.
	 */
	public boolean delete(int id);

	/**
	 * Updates any message in the database.
	 * @param t The message object.
	 * @return boolean Whether or not it was updated.
	 */
	public boolean update(Message t);

	/**
	 * Finds a message using its id.
	 * @param id Id being checked.
	 * @return Message Message object being returned.
	 */
	public Message findById(int id);

	/**
	 * Gets the incoming messages of a user.
	 * @param id Id of the user.
	 * @param type Type of messages to look for. (used for requests as well).
	 * @return List<Message> This is the list of inbox messages returned.
	 */
	public List<Message> getMessages(int id, String type);

	/**
	 * Checks to see if the user has any friend requests.
	 * @param receiverId Id of the self-conscious user.
	 * @return boolean Whether or not they have requests.
	 */
	public boolean checkRequest(int receiverId);

}
