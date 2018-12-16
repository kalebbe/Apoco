/**
 * This class is mostly a stub, but was meant to be used for sending messages and all of the business
 * logic for the messaging service. There are still some things in use here, but not much.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.MessageDAO;
import com.gcu.model.Message;

public class MessageBusinessService implements MessageBusinessInterface {
	@Autowired
	private MessageDAO dao;
	
	/**
	 * This is a method for getting a user's inbox. Currently a stub.
	 * @param id The user's id.
	 * @param type The type of message (ex: read, unread, request, sent).
	 * @return List<Message> Well this is obvious.
	 */
	@Override
	public List<Message> getMessages(int id, String type){
		return dao.getMessages(id, type);
	}
	
	/**
	 * This is a method for creating a new message. Currently used only for friend requests.
	 * @param t This is the message being created.
	 * @return boolean This is whether or not the message was created.
	 */
	@Override
	public boolean create(Message t) {
		return dao.create(t);
	}
	
	/**
	 * This is a method for deleting a message. Currently only used for friend requests.
	 * @param id This is the id of the message.
	 * @return boolean Cmon you know.
	 */
	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}
	
	/**
	 * This method is for updating a message. Will likely be used to change from unread to read. Not in use.
	 * @param t This is the message being updated.
	 * @return boolean Again.
	 */
	@Override
	public boolean update(Message t) {
		return dao.update(t);
	}
	
	/**
	 * This method is for getting a message using its id. Is used for getting user id on friend requests.
	 * @param id The id of the message.
	 * @return Message The message object.
	 */
	@Override
	public Message findById(int id) {
		return dao.findById(id);
	}
	
	/**
	 * This method is for checking if a user has any friend requests.
	 * @param receiverId The id of the logged in user.
	 * @return boolean Whether or not they have requests.
	 */
	@Override
	public boolean checkRequest(int receiverId) {
		return dao.checkRequest(0, receiverId);
	}
}
