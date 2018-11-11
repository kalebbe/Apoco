/**
 * Author:          Kaleb Eberhart
 * Date:            11/04/18
 * Course:          CST-341
 * Project Name:    Apoco
 * Project Version: 1.3
 * Module Name:     DataAccessInterface.java
 * Module Version:  1.0
 * Summary:         This is a base interface for all of the DAO classes to follow with basic methods.
 */

package com.gcu.data;

import java.util.List;

public interface DataAccessInterface <T> {
	public T findById(int id);
	public List<T> findAll();
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(int id);
}
