/**
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     AccountController.java
 * Module Version:  1.1
 * Summary:         This controller handles the revision of user's profile variables.
 * 					In the future, it will be expanded to include deleting user accounts
 * 					and vaulting user accounts. I will probably also do mod promotions here
 *					as well and account suspensions.
 *
 *					-----UPDATE MILESTONE 4-----
 *					-Refactored for Spring jdbc changes.
 *
 *					-----UPDATE MILESTONE 5-----
 *					-Refactored for session error setting in the controller.
 *					-As of right now, if user's input incorrect information, the model will
 *					 still be updated and the incorrect info will be placed on the page, but they
 *					 will receive an error message. I could change it where it resets to the 
 *					 correct information, but this would require another database call. Also
 *					 these checks are done in html, so it shouldn't matter much.
 */
package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.UserBusinessInterface;
import com.gcu.model.User;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	private UserBusinessInterface us;
	
	/**
	 * Dependency injection that instantiates allows me to get the UserBusinessService without
	 * instantianting the object the normal way.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * The /account/edit path that loads the editAccount view. Nothing to see here
	 * really.
	 */
	@RequestMapping(path="/edit", method=RequestMethod.GET)
	public ModelAndView editAccount(HttpSession session) {
		//Returns editAccount with he user's model passed in
		return new ModelAndView("editAccount", "user", us.findById((int)session.getAttribute("id")));
	}
	
	/**
	 * Calls the UserBusinessService method to update the user's first name in the
	 * database.
	 */
	@RequestMapping(path="/updateFirst", method=RequestMethod.POST)
	public ModelAndView updateFirst(@RequestParam("firstName") String first,  HttpSession session) {
		User user = us.findById((int)session.getAttribute("id")); //Get's the user's model from their session id
		user.setFirstName(first); //Updates model to new firstname
		if(!us.updateFirst(user)) {
			session.setAttribute("message", "First name must be between 2 and 30 characters!");
		}
		else {
			session.setAttribute("message3", "First name updated!"); //Confirmation message
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/**
	 * Calls the UserBusinessService method to update the user's last name in the
	 * database.
	 */
	@RequestMapping(path="/updateLast", method=RequestMethod.POST)
	public ModelAndView updateLast(@RequestParam("lastName") String last, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id")); //Grabs old user
		user.setLastName(last); //Updates user with new lastName
		if(!us.updateLast(user)) {
			session.setAttribute("message", "Last name must be between 2 and 30 characters!");
		}
		else {
			session.setAttribute("message3", "Last name updated!"); //Confirmation message
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/**
	 * Calls the UserBusinessService method to check if the new email exists and then
	 * updates the email in the database if it does not exist.
	 */
	@RequestMapping(path="/updateEmail", method=RequestMethod.POST)
	public ModelAndView updateEmail(@RequestParam String email, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		user.setEmail(email);
		if(us.checkEmail(user)) { //Checks to see if the email is taken
			session.setAttribute("message", "Email is already taken!");
			return new ModelAndView("editAccount", "user", user);
		}
		if(!us.updateEmail(user)) {
			session.setAttribute("message", "That is not a valid email!"); //Email does not fit format
		}
		else {
			session.setAttribute("message3", "Email updated!"); //Confirmation message
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/**
	 * Calls the UserBusinessService to check if the username exists and then
	 * updates the dao with the new username.
	 */
	@RequestMapping(path="/updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam String username, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		user.setUsername(username);
		if(us.checkUser(user)) { //Checks to see if username is taken
			session.setAttribute("message", "Username is already taken!");
			return new ModelAndView("editAccount", "user", user);
		}
		if(!us.updateEmail(user)) {
			//Username does not fit size format
			session.setAttribute("message", "Username must be between 4 and 30 characters!");
		}
		else {
			session.setAttribute("message3", "Username updated!"); //Confirmation message
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/**
	 * Checks to see if the current password is correct, the passwords match, and the password matches the regex requirement
	 * of atleast one letter and number and 8 characters.
	 */
	@RequestMapping(path="/updatePass", method=RequestMethod.POST)
	public ModelAndView updatePass(@RequestParam String oldPass, @RequestParam String pass, @RequestParam("rePass") String rePass, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id")); //Grabs old user from databse
		if(!us.checkPass(oldPass, (int)session.getAttribute("id"))){ //Checks to see if the old pass matches DB pass
			session.setAttribute("message", "Current password is incorrect!");
			return new ModelAndView("editAccount", "user", user);
		}
		if(!pass.equals(rePass)) { //Checks to see if password and rePass matches
			session.setAttribute("message", "Passwords must match!");
			return new ModelAndView("editAccount", "user", user);
		}
		user.setPassword(pass);
		user.setPassRe(rePass); //Updates user model with new password
		if(!us.changePass(user)){ //Checks if password passes regex and is 8 characters
			session.setAttribute("message", "Password must be 8 characters and contain letters and numbers!");
		}
		else {
			session.setAttribute("message3", "Password updated!"); //Confirmation message
		}
		return new ModelAndView("editAccount", "user", us.findById((int)session.getAttribute("id")));
	}
}