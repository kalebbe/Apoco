package com.gcu.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.BusinessDAO;
import com.gcu.model.Business;

public class BusinessService implements BusinessInterface {
	
	@Autowired
	private BusinessDAO dao;
	
	@Override
	public boolean createBusiness(Business t) {
		return dao.create(t);
	}
	
	@Override
	public boolean checkBusiness(int id) {
		if(dao.findById(id) != null) {
			return true;
		}
		else {
			return false;
		}
	}
}
