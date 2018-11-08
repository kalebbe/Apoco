/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     SocialBusinessService.java
 * Module Version:  1.0
 * Summary:         This business service handles most general traffic coming from the social
 * 					service on the website. The class implements the SocialBuisnessInterface and
 * 					the methods from that interface.
 * 
 * 					-----UPDATE MILESTONE 4-----
 * 					-Added DAO dependency injection.
 * 					-Removed feed logic from Business service.
 */

package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;
import com.gcu.data.SocialDAO;
import com.gcu.model.Social;

public class SocialBusinessService implements SocialBusinessInterface {

	@Autowired
	private SocialDAO dao;

	/*
	 * This method invokes the create method in SocialDAO to create a new social profile for the
	 * user submitting their information.
	 */
	@Override
	public boolean create(Social t) {
		return dao.create(t);
	}

	/*
	 * This method checks to see if the user has created a social account. If they have, their navbar will
	 * allow them to pages that are restricted to others.
	 */
	@Override
	public boolean checkSocial(int id) {
		if(dao.findById(id) != null) { //Null is returned if exception is caught
			return true;
		}
		else {
			return false;
		}
	}
}
