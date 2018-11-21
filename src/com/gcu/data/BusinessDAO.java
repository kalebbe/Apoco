package com.gcu.data;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gcu.model.Business;

public class BusinessDAO implements DataAccessInterface<Business> {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemp;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemp = new JdbcTemplate(dataSource);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Business findById(int id) {
		try {
			String sql = "SELECT * FROM busprofiles WHERE USER_ID=?";
			Business bus = (Business) jdbcTemp.queryForObject(sql, new Object[] { id }, new BusinessMapper());
			return bus;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Business> findAll() {
		String sql = "SELECT * FROM busprofiles";
		List<Business> bus = jdbcTemp.query(sql, new BusinessMapper());
		return bus;
	}

	@Override
	public boolean create(Business t) {
		String sql = "INSERT INTO busprofiles (USER_ID, DOB, GENDER, ETHNICITY, LOCATION, EDUCATION, PROFESSION)"
				+ " VALUES (?,?,?,?,?,?,?)";
		boolean result = false;
		String location = t.getCity() + ", " + t.getState();
		if (jdbcTemp.update(sql, t.getDob(), t.getGender(), t.getEthnicity(), location, t.getEducation(),
				t.getProfession()) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean update(Business t) {
		String sql = "UPDATE busprofiles SET DOB = ?, GENDER = ?, ETHNICITY = ?, LOCATION = ?, EDUCATION = ?,"
				+ " PROFESSION = ? WHERE ID = ?";
		boolean result = false;
		String location = t.getCity() + ", " + t.getState();
		if (jdbcTemp.update(sql, t.getDob(), t.getGender(), t.getEthnicity(), location, t.getEducation(),
				t.getProfession(), t.getId()) == 1) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean delete(int id) {
		String sql = "DELETE FROM busprofiles WHERE USER_ID = ?";
		boolean result = false;
		if (jdbcTemp.update(sql, id) == 1) {
			result = true;
		}
		return result;
	}

}
