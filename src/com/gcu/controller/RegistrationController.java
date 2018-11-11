/**
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
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
	
	/**
	 * Dependency injection allows useage of User service without instantiating an object every time
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	/**
	 * Sends the user to the Registration page
	 */
	@RequestMapping(path="/reg", method=RequestMethod.GET)
	public ModelAndView Registration() {
		return new ModelAndView("registration", "user", new User());
	}
	
	/**
	 * This method checks the User model data validation and returns them to the registration page if anything is incorrect.
	 * If the user does not have JavaScript disabled in their browser, none of the validation errors should pop up because
	 * they are also checked in the browser.
	 */
	@RequestMapping(path="/register", method=RequestMethod.POST)
	public ModelAndView Register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if(result.hasErrors()) { //User object has errors
			return new ModelAndView("registration", "user", user);
		}
		if(us.checkEmail(user)) { //Checks if the user's email is taken
			session.setAttribute("message", "Email is already taken!");
			return new ModelAndView("registration", "user", user);
		}
		if(us.checkUser(user)) { //Checks if the user's username is taken
			session.setAttribute("message2", "Username is already taken!");
			return new ModelAndView("registration", "user", user);
		}
		if(user.getPassword().equals(user.getPassRe())) { //Ensure matching passwords. Regex checked in model
			session.setAttribute("message3", "Passwords must match!");
			return new ModelAndView("registration", "user", user);
		}
		int id = us.register(user); //Returns the id created by user registration
		if(id != 0) {
			session.setAttribute("id", id); //Sets session id for grabbing user's data later
			return new ModelAndView("userHome", "user", user);
		}
		else {
			session.setAttribute("message", "Something went wrong!"); //Database error
			return new ModelAndView("registration", "user", user);
		}
	}
}