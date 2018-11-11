/**
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     LoginController.java
 * Module Version:  1.01
 * Summary:         This controller handles user login and gives them an error message if their
 * 					login information is incorrect.
 * 
 * 					-----UPDATE MILESTONE 3-----
 * 					-Added a redirect method, so users will know why they are  sent to the login page
 * 					 when they click links they cannot access.
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-User is now sent to home with their user model.
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.SocialBusinessInterface;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private UserBusinessInterface us; //Changed to private per rubric feedback
	private SocialBusinessInterface ps;
	
	/**
	 * Dependency injection for the UserBusinessService.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	} 
	
	/**
	 * Dependency injection for the SocialBusinessService.
	 */
	@Autowired
	public void setProfileService(SocialBusinessInterface ps) {
		this.ps = ps;
	}
	
	/**
	 * Points the user to the login page with no messages.
	 */
	@RequestMapping(path="/log", method=RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	/**
	 * This method calls the UserService and performs the required login
	 * actions there. If the user's login information is correct, the user will
	 * be sent to their home page. Otherwise, the user will be given an error
	 * message and sent back to the login page.
	 */
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam String email, @RequestParam String password, HttpSession session) {
		int id = us.login(email, password); //Checks user login and returns their id if login is correct
		if(id > 0) {
			if(ps.checkSocial(id)) { //Checks if the user has a social profile
				session.setAttribute("hasSocial", true);
			}
			session.setAttribute("id", id); //Sets user id for grabbing data
			return new ModelAndView("userHome", "user", us.findById(id));
		}
		else { //id==0 if the user/pass combo doesn't match. No 0 IDs in the database
			session.setAttribute("message", "Username/password combination is incorrect!");
			return new ModelAndView("login", "user", new User());
		}
	}
	
	/**
	 * This redirection sends the user to the login page with a message telling them why they
	 * were sent there.
	 */
	@RequestMapping(path="/redirect", method=RequestMethod.GET)
	public String redirect(HttpSession session) {
		session.setAttribute("message", "You must be logged in to do that!");
		return "login";
	}
}