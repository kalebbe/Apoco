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
		
		String date = rs.getString("DOB");
		String arr[] = date.split("/");
		
		bus.setId(rs.getInt("ID"));
		bus.setBirthDay(Integer.parseInt(arr[1]));
		bus.setBirthMonth(Integer.parseInt(arr[0]));
		bus.setBirthYear(Integer.parseInt(arr[2]));
		bus.setGender(rs.getString("GENDER"));
		bus.setEthnicity(rs.getString("ETHNICITY"));
		bus.setCity(rs.getString("CITY"));
		bus.setState(rs.getString("STATE"));
		bus.setEducation(rs.getString("EDUCATION"));
		bus.setProfession(rs.getString("PROFESSION"));
		return bus;
	}
}
