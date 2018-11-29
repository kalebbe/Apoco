/**
 * This class was removed from the SocialBusinessService to separate
 * feed from social logic. Basically, this class will be used to
 * return all feed, user feed, create feed, update feed (if used),
 * and delete feed.
 * 
 * -----UPDATE MILESTONE 5-----
 * -Feed can now be updated
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.FeedDAO;
import com.gcu.model.Feed;

public class FeedBusinessService implements FeedBusinessInterface {

	@Autowired
	private FeedDAO dao; //DAO dependency injection

	/**
	 * This method calls the create method in the feedDAO
	 * to create a new feed post. If the user chooses to
	 * add a link to the post, it will be trimmed here to
	 * make it so the link will have a youtube player.
	 * This will need logic in the future to check the
	 * validity of the link.
	 * @param t This is the feed object being created.
	 * @return boolean This is whether or not the object was created successfully.
	 */
	@Override
	public boolean create(Feed t) {
		if (t.getLink() != null) { //Link set to null if none entered
			String[] parts = t.getLink().split(".com/"); //Grabbing everything after .com if existent
			if (1 < parts.length) {
				t.setLink(parts[1]);
			}
			parts = t.getLink().split(".be/"); //Grabbing everything after .be if existent
			if (1 < parts.length) {
				t.setLink(parts[1]);
			}
		}
		return dao.create(t);
	}

	/**
	 * findUserFeed returns the feed of a specific user based upon their user id.
	 * findAll may be used in the future for other reasons, but this method will still
	 * be in use. This method will also be used to grab the feed of user's friends.
	 * @param id This is the id of the user who's feed is being grabbed.
	 * @return List<Feed> This is the list of feed objects returned.
	 */
	@Override
	public List<Feed> findUserFeed(int id) {
		return dao.findUserFeed(id);
	}

	/**
	 * This method is used to delete a user's feed post. The DAO will return true or
	 * false if the deletion is completed.
	 * @param id This is the id of the Feed being deleted.
	 * @return boolean This is the result of Feed deletion.
	 */
	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}
	
	/**
	 * This method takes in a feed object and then updates the object through the DAO.
	 * All updates are completed in the controller
	 * @param t This is the Feed object being sent for update
	 * @return boolean This is the result of the Feed update.
	 */
	@Override
	public boolean update(Feed t) {
		return dao.update(t);
	}
	
	/**
	 * This method was a placeholder in the DAO prior to milestone 5, but is now in use
	 * for the purpose of updating the Feed. When I update a feed object, all of the feed
	 * information is required.
	 * @param id This is the id of the Feed being grabbed from the database.
	 * @return Feed This is the Feed being returned.
	 */
	@Override
	public Feed findById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public String voted(int fId, int uId) {
		return dao.voted(fId, uId);
	}
	
	@Override
	public boolean createVote(Feed t, int uId, String vote) {
		if(!dao.createVote(t.getId(), uId, vote)) {
			return false;
		}
		return dao.update(t);
	}
	
	@Override
	public boolean deleteVote(Feed t, int uId) {
		if(!dao.deleteVote(t.getId(), uId)) {
			return false;
		}
		return dao.update(t);
	}
	
	@Override
	public List<Feed> setVoted(int uId, List<Feed> feedList){
		for(Feed f: feedList) {
			f.setVote(this.voted(f.getId(), uId));
		}
		return feedList;
	}
}
