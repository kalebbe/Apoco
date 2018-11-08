/*
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     HomeController.java
 * Module Version:  1.05
 * Summary:         This controller is used to send the user back to their home page
 * 					when they are logged in. In the future, this will be used to send
 * 					mods and admin to their respective home pages.
 * 
 * 					-----UPDATE MILESTONE 3-----
 * 					-Nothing huge here, just changed it to whipe theme from the session
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-User's account info was removed from session and now returned through User model.
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.UserBusinessInterface;


@Controller
@RequestMapping("/home")
public class HomeController {
	
private UserBusinessInterface us;
	
	/*
	 * Dependency injection allows useage of User service without instantiating an object every time
	 */
	@Autowired
	public void setUserService(UserBusinessInterface us) {
		this.us = us;
	}
	
	
	/*
	 * This method sends the user to their home page. The user's model
	 * is also sent to the home page to display their username.
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public ModelAndView goHome(HttpSession session) {
		session.setAttribute("theme", null);
		return new ModelAndView("userHome", "user", us.findById((int)session.getAttribute("id")));
	}
}
