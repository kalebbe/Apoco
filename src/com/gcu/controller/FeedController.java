/**
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     FeedController.java
 * Module Version:  1.0
 * Summary:         This controller handles all of the controlling for feed posts. This was 
 * 					removed from the SocialController because I felt it deserved its own controller.
 * 
 * 					-----UPDATE MILESTONE 5-----
 * 					-Users can now update their feed post. Link is not editable.
 */

package com.gcu.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.FeedBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Feed;
import com.gcu.model.User;

@Controller
@RequestMapping("/feed")
public class FeedController {
	
	/**
	 * Dependency injection for FeedBusInt and UserBusInt + setters.
	 * Changed to private per rubric
	 */
	private FeedBusinessInterface fs;
	private UserBusinessInterface us;
	
	@Autowired
	public void setFeedService(FeedBusinessInterface fs) {
		this.fs = fs;
	}
	
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * This method is used to display the feed page of the user. Right now, it will
	 * only show the posts of the user logged in, but in the future it will show the
	 * posts of the user's friends.
	 */
	@RequestMapping(path="/feed", method = RequestMethod.GET)
	public ModelAndView displayFeed(@ModelAttribute("feed") Feed feed, HttpSession session) {
		//Grabs all user's feed. This will be changed soon to include friends and public depending on settings.
		List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method validates the model and then appends the feed object to the database if everything is correct.
	 * This will include the possibility of adding a picture in a coming update, but may be pushed for business portion
	 * of website.
	 */
	@RequestMapping(path="createFeed", method=RequestMethod.POST)
	public ModelAndView postFeed(@Valid @ModelAttribute("feed") Feed feed, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
			return new ModelAndView("socialFeed", "feedList", feedList);
		}
		if(feed.getLink() == "") { //sets link to null if the user doesn't put anything in
			feed.setLink(null);
		}
		User user = us.findById((int)session.getAttribute("id")); //Gets current user's model
		String name = user.getFirstName() + " " + user.getLastName(); //Sets users name to feed
		feed.setName(name);
		feed.setUserId((int)session.getAttribute("id")); //Sets user's id to their session id
		if(fs.create(feed)) { //Creates a new feed post
			session.setAttribute("message", "Your post has been created successfully!");
		}
		else {
			//This will be changed in the future to be a red message rather than standard black text.
			session.setAttribute("message", "Something went wrong!");
		}
		
		List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method just deletes the selected post from the user's feed. They have to be the creator of the post, but that's
	 * all they can see right now, so it does not matter. In the future I intend to show friends' posts on their feed
	 */
	@RequestMapping(path="deleteFeed", method=RequestMethod.POST)
	public ModelAndView deleteFeed(@ModelAttribute("feed") Feed feed, @RequestParam("id") int id, HttpSession session) {
		if(fs.delete(id)) { //User deletes their feed. Currently no warning message
			session.setAttribute("message3", "You have deleted your post successfully!");
		}
		else {
			session.setAttribute("message2", "Something went wrong!"); //Database error. Usually a SQLException handled by globalexchandler
		}
		List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id"));
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
	
	/**
	 * This method was added with Milestone 5 to satisfy CRUD requirements and some jquery stuff I wanted to do personally.
	 * It sets feed to "" after because otherwise the top feed will contain the updated feed post for some reason. Post and id
	 * are passed via old school forms because <form:textarea> cannot contain a value in our version of spring and updating to 
	 * spring 5 caused many failures due to some other dependency and I couldn't figure that out.
	 */
	@RequestMapping(path="updateFeed", method=RequestMethod.POST)
	public ModelAndView updateFeed(@ModelAttribute("feed") Feed feed, @RequestParam("id") int id, @RequestParam("feed") String post, HttpSession session) {
		Feed newFeed = fs.findById(id); //Set to newFeed to avoid modelAttribute collision
		newFeed.setFeed(post); //Changes the post portion of the Feed object.
		if(post.length() >= 20) { //Ensures the post is atleast 20 charactesr
			if(fs.update(newFeed)) {
				session.setAttribute("message3", "Your post has been updated!");
			}
			else {
				session.setAttribute("message2", "Something went wrong!"); //Database error
			}
		}
		else {
			session.setAttribute("message2", "Your post must be atleast 20 characters!");
		}
		
		List<Feed> feedList = fs.findUserFeed((int)session.getAttribute("id")); //Grabs updated feedlist
		feed.setFeed(""); //See method comment
		return new ModelAndView("socialFeed", "feedList", feedList);
	}
}
