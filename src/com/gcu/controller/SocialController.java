/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.1
 * Module Name:     SocialController.java
 * Module Version:  1.01
 * Summary:         This controller is used to handle most things that are involved with the
 * 					social service in this project. Will be expanded in the future for more
 * 					social functions. Either that or the controller will be chopped into smaller controllers.
 * 					
 * 					-----UPDATES MILESTONE 4-----
 * 					-Feed logic removed from SocialController and pushed to its own controller.
 */
package com.gcu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.gcu.business.SocialBusinessInterface;
import com.gcu.model.Social;

@Controller
@RequestMapping("/social")
public class SocialController {

	private SocialBusinessInterface ss;

	/*
	 * Dependency injection for the SocialBusinessService
	 */
	@Autowired
	public void setProfileService(SocialBusinessInterface ss) {
		this.ss = ss;
	}

	/*
	 * Sets the user's theme to social which changes their navbar and footer to green. It then checks if the
	 * user has a profile and then either sends them to the creator or the dashboard.
	 */
	@RequestMapping(path = "/social", method = RequestMethod.GET)
	public ModelAndView socialProfile(@ModelAttribute("social") Social social, HttpSession session) {
		session.setAttribute("theme", "social");
		if(session.getAttribute("hasSocial") != null) {
			return new ModelAndView("socialDash", "social", new Social());
		}
		else {
			return new ModelAndView("socialProfile", "social", new Social());
		}
	}
	
	/*
	 * Used to load the games tab of the social service
	 */
	@RequestMapping(path = "/games", method = RequestMethod.GET)
	public String getGames() {
		return "playGames";
	}

	/*
	 * Submits the user's social profile for entrance into the database after going through the profile service.
	 * This will use a picture in the future, but I've already spent well over 30 hours on this milestone alone and
	 * I'm done. Will try again later once we're into Maven. I downloaded all the jars and added the crap to the config
	 * and it still didn't work. ah well
	 */
	@RequestMapping(path = "/submitSocial", method = RequestMethod.POST)
	public ModelAndView submitSocial(@Valid @ModelAttribute("social") Social social, BindingResult result,
			HttpSession session) {

		if (result.hasErrors()) {
			return new ModelAndView("socialProfile", "social", social);
		}
		session.setAttribute("social", social);
		session.setAttribute("hasSocial", true);

		/*
		 * File dir = new File("F:Workspaces/CST341/Apoco/WebContent/assets/img/social/" + (String) session.getAttribute("userId")); dir.mkdirs(); try { mpf.transferTo(dir); }
		 * catch (IllegalStateException | IOException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); System.out.println(e); }
		 */
		social.setUserId((int)session.getAttribute("id"));
		ss.create(social);
		
		return new ModelAndView("socialDash", "social", social);
	}

	/*
	 * Gives a list from 1 to 31 for a dropdown in the view.
	 */
	@ModelAttribute("dayList")
	public List<Integer> getBirthDay() {
		List<Integer> dayList = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) {
			dayList.add(i);
		}
		return dayList;
	}
	
	/*
	 * Returns a list of years for a dropdown in the view
	 */
	@ModelAttribute("yearList")
	public List<Integer> getBirthYear(){
		List<Integer> yearList = new ArrayList<Integer>();
		for(int i = 2018; i >= 1900; i--) {
			yearList.add(i);
		}
		return yearList;
	}
	
	/*
	 * Returns a list of months for a dropdown in the view. Each month
	 * is given a numeric value.
	 */
	@ModelAttribute("monthList")
	public Map<Integer, String> getMonthList(){
		Map<Integer, String> monthList = new HashMap<Integer, String>();
		monthList.put(1, "January");
		monthList.put(2, "February");
		monthList.put(3, "March");
		monthList.put(4, "April");
		monthList.put(5, "May");
		monthList.put(6, "June");
		monthList.put(7, "July");
		monthList.put(8, "August");
		monthList.put(9, "September");
		monthList.put(10, "October");
		monthList.put(11, "November");
		monthList.put(12, "December");
		return monthList;
	}
	
	/*
	 * Returns a generic list of job types for the user to choose from as a career field.
	 */
	@ModelAttribute("jobList")
	public List<String> getJobList(){
		List<String> jobList = new ArrayList<String>();
		jobList.add("Accounting");
		jobList.add("Admin");
		jobList.add("Automotive");
		jobList.add("Banking");
		jobList.add("Biotech");
		jobList.add("Business");
		jobList.add("Construction");
		jobList.add("Consultant");
		jobList.add("Customer Service");
		jobList.add("Design");
		jobList.add("Distribution");
		jobList.add("Education");
		jobList.add("Engineering");
		jobList.add("Facilities");
		jobList.add("Finanace");
		jobList.add("Franchise");
		jobList.add("General Labor");
		jobList.add("Government");
		jobList.add("Grocery");
		jobList.add("Health Care");
		jobList.add("Human Resources");
		jobList.add("Installation");
		jobList.add("Repair");
		jobList.add("Insurance");
		jobList.add("Journalism");
		jobList.add("Legal");
		jobList.add("Management");
		jobList.add("Manufacturing");
		jobList.add("Marketing");
		jobList.add("Non-profit");
		jobList.add("Pharmaceutical");
		jobList.add("Quality Assurance");
		jobList.add("Real Estate");
		jobList.add("Research");
		jobList.add("Restaurant/Food Service");
		jobList.add("Retail");
		jobList.add("Sales");
		jobList.add("Science");
		jobList.add("Shipping");
		jobList.add("Technology");
		jobList.add("Telecommunications");
		jobList.add("Training");
		jobList.add("Transportation");
		jobList.add("Warehouse");
		jobList.add("Other");
		return jobList;
	}
	
	/*
	 * Returns a list of education levels for the user to choose from
	 */
	@ModelAttribute("edList")
	public List<String> getEdList(){
		List<String> edList = new ArrayList<String>();
		edList.add("None");
		edList.add("Elementary");
		edList.add("Middle School");
		edList.add("Some High School");
		edList.add("General Education Development");
		edList.add("High School Diploma");
		edList.add("Some College");
		edList.add("Associates Degree");
		edList.add("Bachelor's Degree");
		edList.add("Master's Degree");
		edList.add("Doctorate's Degree");
		return edList;
	}
	
	/*
	 * Returns a list of relationship statuses for the user to choose from.
	 */
	@ModelAttribute("statusList")
	public List<String> getStatList(){
		List<String> statList = new ArrayList<String>();
		statList.add("Single");
		statList.add("In a relationship");
		statList.add("Engaged");
		statList.add("Married");
		statList.add("Separated");
		statList.add("Divorced");
		statList.add("Widowed");
		return statList;
	}
	
	/*
	 * Returns a list of state initials for the user to choose from
	 */
	@ModelAttribute("stateList")
	public List<String> getStates(){
		List<String> stateList = new ArrayList<String>();
		stateList.add("AL");
		stateList.add("AK");
		stateList.add("AZ");
		stateList.add("AR");
		stateList.add("CA");
		stateList.add("CO");
		stateList.add("CT");
		stateList.add("DE");
		stateList.add("FL");
		stateList.add("GA");
		stateList.add("HI");
		stateList.add("ID");
		stateList.add("IL");
		stateList.add("IN");
		stateList.add("IA");
		stateList.add("KS");
		stateList.add("KY");
		stateList.add("LA");
		stateList.add("ME");
		stateList.add("MD");
		stateList.add("MA");
		stateList.add("MI");
		stateList.add("MN");
		stateList.add("MS");
		stateList.add("MO");
		stateList.add("MT");
		stateList.add("NE");
		stateList.add("NV");
		stateList.add("NH");
		stateList.add("NJ");
		stateList.add("NM");
		stateList.add("NY");
		stateList.add("NC");
		stateList.add("ND");
		stateList.add("OH");
		stateList.add("OK");
		stateList.add("OR");
		stateList.add("PA");
		stateList.add("RI");
		stateList.add("SC");
		stateList.add("SD");
		stateList.add("TN");
		stateList.add("TX");
		stateList.add("UT");
		stateList.add("VT");
		stateList.add("VA");
		stateList.add("WA");
		stateList.add("WV");
		stateList.add("WI");
		stateList.add("WY");
		return stateList;
	}
}
