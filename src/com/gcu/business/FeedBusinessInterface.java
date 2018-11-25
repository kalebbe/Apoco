/**
 * This interface lays forth the method guidelines for the FeedBusinessService class.
 * 
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-11-25
 */

package com.gcu.business;

import java.util.List;
import com.gcu.model.Feed;

public interface FeedBusinessInterface {
	
	/**
	 * This method calls the DAO to create a new Feed object.
	 * @param t This is the feed object being created.
	 * @return boolean This is the result of the database insertion.
	 */
	public boolean create(Feed t);
	
	/**
	 * This method gets a user's feed objects from the database.
	 * @param id This is the user's ID to grab feed objects from.
	 * @return List<Feed> This is the list of feed objects returned.
	 */
	public List<Feed> findUserFeed(int id);
	
	/**
	 * This method calls the DAO to delete the Feed object.
	 * @param id This is the feed's ID being deleted.
	 * @return boolean This is the result of the database object deletion.
	 */
	public boolean delete(int id);
	
	/**
	 * This method calls the DAO to update the Feed object.
	 * @param t This is the feed's ID being updated.
	 * @return boolean This is the result of the database object update.
	 */
	public boolean update(Feed t);
	
	/**
	 * This method finds a Feed object by its specific id using the DAO.
	 * @param id This is the ID of the specific feed object.
	 * @return Feed this is the Feed object returned.
	 */
	public Feed findById(int id);
}
