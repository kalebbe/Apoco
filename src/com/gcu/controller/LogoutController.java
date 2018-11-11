/**
 * Author:          Kaleb Eberhart
 * Date:            09/23/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     LogoutController.java
 * Module Version:  1.01
 * Summary:         This controller handles the user logout and deletes all of the session
 * 					information. This controller causes a known bug that will be fixed in
 * 					the upcoming milestone. The user is forwarded to /logout/login which
 * 					causes errors if the user tries to log in from there. Bug can be avoided
 * 					by clicking the login link after reaching the logout/login page.
 * 
 * 					-----UPDATE MILESTONE 5-----
 * 					-Small update- changed to a redirect, so there aren't problems with paths
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	/**
	 * Clears the session and sends user to the login page.
	 */
	@RequestMapping(path="/log", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate(); //Clears session
		return "redirect:../login/log"; //Redirects user
	}
}
