/*
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     RegistrationController.java
 * Module Version:  1.01
 * Summary:         This controller handles the user's registration to my website and
 * 					also brings them to the registration page.
 * 					
 * 					-----UPDATE MILESTONE 3-----
 * 					-Added Dependency injection
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.UserBusinessInterface;
import com.gcu.model.User;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
	
	UserBusinessInterface us;
	
	/*
	 * Dependency injection allows useage of User service without instantiating an object every time
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/*
	 * Sends the user to the Registration page
	 */
	@RequestMapping(path="/reg", method=RequestMethod.GET)
	public ModelAndView Registration() {
		return new ModelAndView("registration", "user", new User());
	}
	
	/*
	 * This method checks the User model data validation and returns them to the registration page if anything is incorrect.
	 * If the user does not have JavaScript disabled in their browser, none of the validation errors should pop up because
	 * they are also checked in the browser.
	 */
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public ModelAndView Register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return new ModelAndView("registration", "user", user);
		}
		if(us.checkEmail(user)) {
			session.setAttribute("message", "Email is already taken!");
			return new ModelAndView("registration", "user", user);
		}
		if(us.checkUser(user)) {
			session.setAttribute("message2", "Username is already taken!");
			return new ModelAndView("registration", "user", user);
		}
		if(user.getPassword().equals(user.getPassRe())) {
			session.setAttribute("message3", "Passwords must match!");
			return new ModelAndView("registration", "user", user);
		}
		int id = us.create(user);
		if(id != 0) {
			return new ModelAndView("userHome", "user", user);
		}
		else {
			session.setAttribute("message", "Something went wrong!");
			return new ModelAndView("registration", "user", user);
		}
	}
}