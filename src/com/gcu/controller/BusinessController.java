/**
 * This controller is used to handle all things that are involved with the business profile
 * in this project. This will likely be expanded in the future to include updating and
 * deleting profiles.
 * 
 * @author  Kaleb Eberhart
 * @version 1.0
 * @since   2018-11-25
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
import com.gcu.business.BusinessInterface;
import com.gcu.model.Business;

@Controller
@RequestMapping("/business")
public class BusinessController {
	private BusinessInterface bs;
	
	/**
	 * Dependency injection for the BusinessService
	 * @param bs This is the class being set.
	 * @return Nothing.
	 */
	@Autowired
	public void setBusinessService(BusinessInterface bs) {
		this.bs = bs;
	}
	
	/**
	 * Sets the user's theme to business which will change the appearance of their navbar and footer.
	 * It will then check to see if the user has a business profile and either sends them to the profile
	 * creator or the business dashboard.
	 * @param bus This is the business model retrieved from the view.
	 * @param session This is the session used to check if the user has a business profile.
	 * @return ModelAndView This is the new business object sent to the busProfile view.
	 */
	@RequestMapping(path = "/bus", method = RequestMethod.GET)
	public ModelAndView businessProfile(@ModelAttribute("business") Business bus, HttpSession session) {
		session.setAttribute("theme", "business");
		if(session.getAttribute("hasBusiness") != null) {
			//Dashboard is returned if the user has a business profile
			return new ModelAndView("busDash", "business", new Business());
		}
		else {
			return new ModelAndView("busProfile", "business", new Business()); //To the creator
		}
	}
	
	/**
	 * Submits the user's business profile for insertion into the database after pushing through the business
	 * service. Pictures will be in an upcoming milestone.
	 * @param bus This is the business model grabbed from the view.
	 * @param result This is the result used to check for errors.
	 * @param session This is the session used to set the hasBusiness variable.
	 * @return ModelAndView This is the updated business object with errors being sent back to the busProfile view.
	 */
	@RequestMapping(path = "/submitBus", method = RequestMethod.POST)
	public ModelAndView submitBus(@Valid @ModelAttribute("business") Business bus, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return new ModelAndView("busProfile", "business", bus); //REturns busProfile with errors
		}
		
		bus.setUserId((int)session.getAttribute("id"));
		
		if(bs.createBusiness(bus)) {
			session.setAttribute("hasBusiness", true); //This allows the user to land at the dashboard when they got to business
			return new ModelAndView("busDash", "business", bus);
		}
		else { //Business profile failed insertion into database.
			return new ModelAndView("busProfile", "business", new Business());
		}
	}
	
	/**
	 * Gives a list from 1 to 31 for a drop down in the view.
	 * @return List<Integer> This is the list of days in a month being returned.
	 */
	@ModelAttribute("dayList")
	public List<Integer> getBirthDay() {
		List<Integer> dayList = new ArrayList<Integer>();
		for (int i = 1; i <= 31; i++) { //Loop to grab numbers 1->31
			dayList.add(i);
		}
		return dayList;
	}
	
	/**
	 * Returns a list of years for a dropdown in the view. I don't
	 * think anyone is 118 years old that will be registering, but 
	 * that's all right.
	 * @return List<Integer> This is a list of many years being returned.
	 */
	@ModelAttribute("yearList")
	public List<Integer> getBirthYear(){
		List<Integer> yearList = new ArrayList<Integer>();
		for(int i = 2018; i >= 1900; i--) { //Loops 2018 -> 1900 descending
			yearList.add(i);
		}
		return yearList;
	}
	
	/**
	 * Returns a list of months for a dropdown in the view. Each month
	 * is given a numeric value.
	 * @return Map<Integer, String> This is a map of months->value pairs being returned.
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
	
	/**
	 * Returns a generic list of job types for the user to choose from as a career field.
	 * @return List<String> This is the list of different job types being returned.
	 */
	@ModelAttribute("jobList")
	public List<String> getJobList(){
		List<String> jobList = new ArrayList<String>();
		//These job types were grabbed from a career webpage and are meant to be generic.
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
	
	/**
	 * This method gives a list of different ethnicities to the model for the user to choose from.
	 * @return List<String> This is the list of ethnicities being returned.
	 */
	@ModelAttribute("ethList")
	public List<String> getEthList(){
		List<String> ethList = new ArrayList<String>();
		ethList.add("American Indian or Alaska Native");
		ethList.add("Asian");
		ethList.add("Black or African American");
		ethList.add("Native Hawaiian or Other Pacific Islander");
		ethList.add("Hispanic or Latino");
		ethList.add("White");
		ethList.add("Other");
		return ethList;
	}
	
	/**
	 * Returns a list of education levels for the user to choose from. None might not really be
	 * necessary for this website, but oh well.
	 * @return List<String> This is the list of education levels being returned.
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
	
	/**
	 * Returns a list of state initials for the user to choose from.
	 * @return List<String> This is the list of state abbreviations being returned.
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
