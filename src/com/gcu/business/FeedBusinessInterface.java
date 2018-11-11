/**
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     FeedBusinessInterface.java
 * Module Version:  1.0
 * Summary:         This interface lays forth the method guidelines for the FeedBusinessService class
 */

package com.gcu.business;

import java.util.List;
import com.gcu.model.Feed;

public interface FeedBusinessInterface {
	public boolean create(Feed t);
	public List<Feed> findUserFeed(int id);
	public boolean delete(int id);
	public boolean update(Feed t);
	public Feed findById(int id);
}
