/**
 * This interface defines the methods required in the FriendsBusinessService class. Will likely be updated
 * before the final project.
 *
 *
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-10
 */
package com.gcu.business;

import java.util.List;

import com.gcu.model.Friend;
import com.gcu.model.User;

public interface FriendBusinessInterface {

	/**
	 * This method returns a list of people based upon the search results.
	 * @param search This is the search term.
	 * @return List<User> This is the list of Users.
	 */
	public List<User> searchPeople(String search);

	/**
	 * This method calls the dao to add a friend to the database.
	 * @param t This is the friend being added.
	 * @return boolean Success or failure of creation.
	 */
	public boolean addFriend(Friend t);

	/**
	 * This method calls the dao to delete a friend from the database.
	 * @param t This is the friend being deleted.
	 * @return Success or failure of deletion.
	 */
	public boolean deleteFriend(Friend t);

	/**
	 * This method checks each user to see if they have a social profile.
	 * @param users This is the list of users being checked.
	 * @param type This is whether the search is for email or names.
	 * @return List<User> This is the list of users returned that have a profile.
	 */
	public List<User> checkProfiles(List<User> users, String type);
}
