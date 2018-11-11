/**
 * Author:          Kaleb Eberhart
 * Date:            11/10/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     GlobalExceptionHandler.java
 * Module Version:  1.0
 * Summary:         This class is used to give instructions to each controller when they receive
 * 					an exception. As of right now, only database exceptions and 500 errors + null pointers
 * 					are being caught here, but it may be updated in the future if required.
 */

package com.gcu.utilities;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice //Guidelines for each controller to follow
public class GlobalExceptionHandler {

	/**
	 * This method handles all SQLExceptions and DataAccessExceptions that are caught through the application.
	 * Users will be redirected to a screen that tells them the database is currently down.
	 */
	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String databaseError(HttpSession session, SQLException e) {
		session.setAttribute("theme", null);
		e.printStackTrace();
		return "databaseError";
	}

	/**
	 * This should only be reachable when the user's session has been timed out if
	 * everything is coded correctly. Otherwise, it still avoids them seeing that
	 * I'm using Spring. The way I understand it, they will only be redirected if
	 * their session times out and they try to access a page that requires a session variable.
	 * I might be wrong about this though.
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //500 error
	@ExceptionHandler(NullPointerException.class)
	public String timeout(HttpSession session) {
		if (session.getAttribute("id") == null) {
			//This should be the only place reachable with this method
			session.setAttribute("message", "Your session has timed out!");
			return "redirect:../login/log"; //Redirects to login page.
		}
		else {
			return "userHome";
		}
	}
}
