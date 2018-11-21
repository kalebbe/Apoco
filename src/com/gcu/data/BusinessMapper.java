package com.gcu.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gcu.model.Business;

@SuppressWarnings("rawtypes")
public class BusinessMapper implements RowMapper {
	@Override
	public Business mapRow(ResultSet rs, int rowNum) throws SQLException {
		Business bus = new Business();
		
		//TODO: Set business items
		return bus;
	}
}
