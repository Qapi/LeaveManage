package com.wp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wp.dao.IEmployeeDao;
import com.wp.domain.Employee;

@Repository
public class EmployeeDaoImpl implements IEmployeeDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**使用用户名作为查询条件，查询用户对象*/
	@Override
	@SuppressWarnings("unchecked")
	public Employee findEmployeeByName(String name) {
		String hql = "from Employee o where o.name =:name";
		List<Employee> list = getSession().createQuery(hql).setParameter("name", name).list();
		Employee employee = null;
		if(list!=null && list.size()>0){
			employee = list.get(0);
		}
		return employee;
	}
}
