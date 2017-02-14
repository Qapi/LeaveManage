package com.wp.dao;

import com.wp.domain.Employee;

public interface IEmployeeDao {

	Employee findEmployeeByName(String name);

	

}
