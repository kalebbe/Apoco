/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     AccountController.java
 * Module Version:  1.1
 * Summary:         This controller handles the revision of user's profile variables.
 * 					In the future, it will be expanded to include deleting user accounts
 * 					and vaulting user accounts. I will probably also do mod promotions here
 *					as well and account suspensions.
 *
 *					-----UPDATE MILESTONE 4-----
 *					-Refactored for Spring jdbc changes.
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
	
	/*
	 * Dependency injection that instantiates allows me to get the UserBusinessService without
	 * instantianting the object the normal way.
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/*
	 * The /account/edit path that loads the editAccount view. Nothing to see here
	 * really.
	 */
	@RequestMapping(path="/edit", method=RequestMethod.GET)
	public ModelAndView editAccount(HttpSession session) {
		return new ModelAndView("editAccount", "user", us.findById((int)session.getAttribute("id")));
	}
	
	/*
	 * Calls the UserBusinessService method to update the user's first name in the
	 * database;
	 */
	@RequestMapping(path="/updateFirst", method=RequestMethod.POST)
	public ModelAndView updateFirst(@RequestParam("firstName") String first,  HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		user.setFirstName(first);
		if(!us.updateFirst(user, (int)session.getAttribute("id"))) {
			session.setAttribute("message", "First name must be between 2 and 30 characters!");
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/*
	 * Calls the UserBusinessService method to update the user's last name in the
	 * database;
	 */
	@RequestMapping(path="/updateLast", method=RequestMethod.POST)
	public ModelAndView updateLast(@RequestParam("lastName") String last, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		user.setLastName(last);
		if(!us.updateLast(user, (int)session.getAttribute("id"))) {
			session.setAttribute("message", "Last name must be between 2 and 30 characters!");
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/*
	 * Calls the UserBusinessService method to update the user's email in the
	 * database;
	 */
	@RequestMapping(path="/updateEmail", method=RequestMethod.POST)
	public ModelAndView updateEmail(@RequestParam String email, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		user.setEmail(email);
		if(us.checkEmail(user)) {
			session.setAttribute("message", "Email is already taken!");
			return new ModelAndView("editAccount", "user", user);
		}
		if(!us.updateEmail(user, (int)session.getAttribute("id"))) {
			session.setAttribute("message", "That is not a valid email!");
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/*
	 * Calls the UserBusinessService method to update the user's username in the
	 * database;
	 */
	@RequestMapping(path="/updateUser", method=RequestMethod.POST)
	public ModelAndView updateUser(@RequestParam String username, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		user.setUsername(username);
		if(us.checkUser(user)) {
			session.setAttribute("message", "Username is already taken!");
			return new ModelAndView("editAccount", "user", user);
		}
		if(!us.updateEmail(user, (int)session.getAttribute("id"))) {
			session.setAttribute("message", "Username must be between 4 and 30 characters!");
		}
		return new ModelAndView("editAccount", "user", user);
	}
	
	/*
	 * Checks the user's old pass with the current input to see if they match. If they do, the user's password
	 * is changed in the database after being hashed.
	 */
	@RequestMapping(path="/updatePass", method=RequestMethod.POST)
	public ModelAndView updatePass(@RequestParam String oldPass, @RequestParam String pass, @RequestParam("rePass") String rePass, HttpSession session) {
		User user = us.findById((int)session.getAttribute("id"));
		if(!us.checkPass(oldPass, (int)session.getAttribute("id"))){
			session.setAttribute("message", "Current password is incorrect!");
			return new ModelAndView("editAccount", "user", user);
		}
		if(!pass.equals(rePass)) {
			session.setAttribute("message", "Passwords must match!");
			return new ModelAndView("editAccount", "user", user);
		}
		user.setPassword(pass);
		user.setPassRe(rePass);
		if(!us.changePass(user, (int)session.getAttribute("id"))){
			session.setAttribute("message", "Password must be 8 characters and contain letters and numbers!");
		}
		return new ModelAndView("editAccount", "user", us.findById((int)session.getAttribute("id")));
	}
}