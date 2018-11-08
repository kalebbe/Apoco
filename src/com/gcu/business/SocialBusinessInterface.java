/*
 * Author:          Kaleb Eberhart
 * Date:            10/14/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.2
 * Module Name:     SocialBusinessInterface.java
 * Module Version:  1.01
 * Summary:         This interface lays forth the method guidelines for the SocialBusinessService class
 */
package com.gcu.business;

import com.gcu.model.Social;

public interface SocialBusinessInterface {
	public boolean create(Social t);
	public boolean checkSocial(int id);
}
