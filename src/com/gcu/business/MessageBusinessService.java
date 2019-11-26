/**
 * This class is mostly a stub, but was meant to be used for sending messages and all of the business
 * logic for the messaging service. There are still some things in use here, but not much.
 * 
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2018-12-16
 */

package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.MessageDAO;
import com.gcu.data.UserDAO;
import com.gcu.model.Message;

public class MessageBusinessService implements MessageBusinessInterface {
	@Autowired
	private MessageDAO dao;
	
	@Autowired
	private UserDAO uDao;
	
	/**
	 * This is a method for getting a user's inbox. Currently a stub.
	 * @param id The user's id.
	 * @param type The type of message (ex: read, unread, request, sent).
	 * @return List<Message> Well this is obvious.
	 */
	@Override
	public List<Message> getMessages(int id, String theme, String type){
		List<Message> msgs = null;
		
		//Will be updated to include dating soon
		if(theme == "social") {
			msgs = dao.getMessages(id, type, "socUnread", "socRead");
		}
		else if (theme == "business") {
			msgs = dao.getMessages(id, type, "busUnread", "busRead");
		}
		else {
			msgs = dao.getMessages(id, type, theme, theme);
			
			return msgs;
		}
		
		if(msgs != null) {
			//Attaching user object for the sender of each message
			for(Message msg : msgs) {
				//This is done to set the max length of the message to 50 characters. Math.min
				//is used to prevent exceptions if the string is shorter than 50 characters.
				msg.setBody(msg.getBody().substring(0, Math.min(msg.getBody().length(), 310)));
				msg.setUser(uDao.findById(msg.getSenderId()));
				
				msg.setRead(this.checkRead(msg.getId(), id));
			}
		}
		
		return msgs;
	}
	
	/**
	 * Calls the database to retrieve a message thread based upon a parent ID
	 * @param id
	 * @return List<Message>
	 */
	@Override
	public List<Message> getThread(int id){
		List<Message> msgs = dao.getThread(id);
		msgs.add(0, dao.findById(id)); //Puts the parent message at the start of the list
		
		//Attach sender User to each message
		for(Message msg : msgs) {
			msg.setUser(uDao.findById(msg.getSenderId()));
		}
		
		return msgs;
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
	 * This method is for updating a message. Will likely be used to change from unread to read.
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
	public boolean checkRequest(int receiverId, String type) {
		return dao.checkRequest(0, receiverId, type);
<<<<<<< Updated upstream
=======
<<<<<<< HEAD
	}
	
	/**
	 * This method gets the specified type of notifcation from the 
	 * database.
	 * @param id
	 * @param type
	 * @return int
	 */
	@Override
	public int getNotifications(int id, String type) {
		return dao.getNotifications(id, type);
	}
	
	/**
	 * The purpose of this method is to call the DAO to create a new message in the database.
	 * This is not the same as sending a friend/connection request, but rather an actual message.
	 * @param sid - The message sender's ID
	 * @param rid - The message receiver's ID
	 * @param pid - The parent message's ID (-1 if this is parent)
	 * @param body
	 * @param theme
	 * @return boolean
	 */
	@Override
	public boolean sendMessage(int sid, int rid, int pid, String body, String theme) {
		
		//Determining the type based upon the current theme (differentiates soc/bus/dating)
		String type = "";
		if(theme == "social") {
			type = "socUnread";
		}
		else if(theme == "business") {
			type = "busUnread";
		}
		
		//Create new message object for database insertion
		Message msg = new Message(sid, rid, pid, body, type);
		
		return dao.create(msg);
	}
	
	/**
	 * This method marks a thread as read when a user opens the thread.
	 * @param t
	 * @param theme
	 * @return boolean
	 */
	@Override
	public boolean markRead(Message t, String theme) {		
		if(theme.equals("social")) {
			t.setType("socRead");
		}
		else if(theme.equals("business")) {
			t.setType("busRead");
		}
		return dao.update(t);
	}
	
	/**
	 * The purpose of this method is to get the other person's ID in a 
	 * message thread. It is needed to continue sending messages to the
	 * other person from the thread.
	 * @param id
	 * @param t
	 * @return int
	 */
	@Override
	public int getCorrespondentId(int id, Message t) {
		if(t.getSenderId() == id) {
			return t.getReceiverId();
		}
		else {
			return t.getSenderId();
		}
	}
	
	/**
	 * 
	 * @param tid ID of the thread (Parent)
	 * @param uid ID of the logged in user
	 * @return boolean
	 */
	public boolean checkRead(int tid, int uid) {
		Message msg = dao.getLastThread(tid);
		
		//User sent the last message, so they've read the thread
		if(uid == msg.getSenderId()) {
			return true;
		}
		else if(msg.getType().equals("socUnread") ||
				msg.getType().equals("busUnread")) {
			return false;
		}
		else {
			return true;
		}
=======
>>>>>>> 5f384a09925701c157caf999ba50900c1a9432af
>>>>>>> Stashed changes
	}
}
