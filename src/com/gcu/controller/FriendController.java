/**
 * This controller is used to handle all things relating to friends in the social platform. This
 * will be updated heavily for the final milestone.
 * 
 *
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-12-10
 */

package com.gcu.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.FriendBusinessInterface;
import com.gcu.model.User;

@Controller
@RequestMapping("/friends")
public class FriendController {
	private FriendBusinessInterface fs;

	/**
	 * Dependency injection that instantiates allows me to get the FriendBusinessService without
	 * instantiating the object the normal way.
	 * @param fs This is the FriendBusinessInterface being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setFriendBusinessService(FriendBusinessInterface fs) {
		this.fs = fs;
	}

	/**
	 * This method is used to search the database for users with a social profile based upon certain search terms.
	 * @param search This is the search term.
	 * @param session This is the session to set the page type.
	 * @return ModelAndView This is the view being forarded to and the models.
	 */
	@RequestMapping(path = "/search", method = RequestMethod.POST)
	public ModelAndView searchResults(@RequestParam String search, HttpSession session) {
		List<User> users = fs.searchPeople(search);
		session.setAttribute("page", "search");
		return new ModelAndView("friendList", "users", users);
	}
}
