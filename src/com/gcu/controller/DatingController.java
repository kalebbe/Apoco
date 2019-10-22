/**
 * This controller is used to handle all things that are involved with the dating profile
 * in this project.
 * 
 * @authors Kaleb Eberhart, Mick Torres
 * @version 1.0
 * @since   2019-10-21
 */

package com.gcu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.User;

@Controller
@RequestMapping("/dating")
public class DatingController {

	/**
	 * This is a method stub for routing a user to their dating dashboard or profile creator.
	 * @param session
	 * @return ModelAndView
	 */
	@RequestMapping(path = "/dat", method = RequestMethod.GET)
	public ModelAndView datingProfile(HttpSession session) {
		if(session.getAttribute("id") == null) {
			return new ModelAndView("redirect:../login/log", "user", new User());
		}
		session.setAttribute("theme", "dating");
		return new ModelAndView("datDash", "dating", null);
	}
}
