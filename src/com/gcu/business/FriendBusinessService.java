/**
 * This class is used to search for friends and will be used to add and delete friends 
 * in the final update.
 *
 *
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-10
 */

package com.gcu.business;

import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.FriendDAO;
import com.gcu.data.UserDAO;
import com.gcu.data.SocialDAO;
import com.gcu.model.Friend;
import com.gcu.model.User;

public class FriendBusinessService implements FriendBusinessInterface {
	@Autowired
	private FriendDAO dao;
	@Autowired
	private UserDAO uDao;
	@Autowired
	private SocialDAO sDao;

	/**
	 * This method is used to search for profiles based upon name or email.
	 * @param search This is the search term being checked.
	 * @return List<User> This is the list of search results.
	 */
	@Override
	public List<User> searchPeople(String search) {
		try {
			InternetAddress email = new InternetAddress(search);
			email.validate(); //Checks to see if the search term is an email address
			List<User> users = new ArrayList<User>();
			if (uDao.searchEmail(search) == null) { //Failed search
				return null;
			}
			users.add(uDao.searchEmail(search)); //Adds the user to the search list
			users = checkProfiles(users, "email");
			return users;
		} catch (AddressException e) {
			List<User> users = uDao.searchUsers(search);
			return checkProfiles(users, "other");
		}
	}

	/**
	 * This method is for checking to see if the searched users have a social profile.
	 * @param users This is the list of users being checked.
	 * @param type This is whether the search is an email or otherwise.
	 * @return List<User> This is the list of users returned that have a profile.
	 */
	@Override
	public List<User> checkProfiles(List<User> users, String type) {
		List<User> newUsers = new ArrayList<User>();
		for (User u : users) { //foreach for the userlist
			if (!type.equals("email")) { //Email overrules the privacy.
				if (sDao.getPrivacy(u.getId()) == 0) {
					if(sDao.findById(u.getId()) != null) {
						u.setSocial(sDao.findById(u.getId()));
						newUsers.add(u);
					}
				}
			}
			else { //Goes here if it's an email search
				if(sDao.findById(u.getId()) != null) {
					u.setSocial(sDao.findById(u.getId()));
					newUsers.add(u);
				}
			}
		}
		return newUsers;
	}

	/**
	 * This method calls the DAO to add a new friend to the database. Currently not in use.
	 * @param t This is the friend being added to the database.
	 * @return boolean This is whether or not the transaction was successful.
	 */
	@Override
	public boolean addFriend(Friend t) {
		return dao.create(t);
	}

	/**
	 * This method calls the DAO to delete a friend from the database. Currently not in use.
	 * @param t This is the friend being deleted.
	 * @return boolean This is whether or not the transaction was successful.
	 */
	@Override
	public boolean deleteFriend(Friend t) {
		return dao.delete(t);
	}
}
