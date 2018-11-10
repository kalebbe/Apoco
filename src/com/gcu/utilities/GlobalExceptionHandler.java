package com.gcu.utilities;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({ SQLException.class, DataAccessException.class })
	public String databaseError(HttpSession session, SQLException e) {
		session.setAttribute("theme", null);
		e.printStackTrace();
		return "databaseError";
	}

	/*
	 * This should only be reachable when the user's session has been timed out if
	 * everything is coded correctly. Otherwise, it still avoids them seeing that
	 * I'm using Spring.
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NullPointerException.class)
	public String timeout(HttpSession session) {
		if (session.getAttribute("id") == null) {
			session.setAttribute("message", "Your session has timed out!");
			return "redirect:../login/log";
		}
		else {
			return "userHome";
		}
	}
}
