package com.gcu.controller;

import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler({SQLException.class, DataAccessException.class})
	public String databaseError(HttpSession session, SQLException e) {
		session.setAttribute("theme", null);
		e.printStackTrace();
		return "databaseError";
	}
}
