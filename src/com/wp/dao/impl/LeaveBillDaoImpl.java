package com.wp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wp.dao.ILeaveBillDao;
import com.wp.domain.Employee;
import com.wp.domain.LeaveBill;
import com.wp.utils.SessionContext;

@Repository
public class LeaveBillDaoImpl implements ILeaveBillDao{

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	/**查询自己的请假单的信息*/
	@SuppressWarnings("unchecked")
	@Override
	public List<LeaveBill> findLeaveBillList() {
		//从Session中获取当前用户
		Employee employee = SessionContext.get();
		String hql = "from LeaveBill o where o.user=:employee";//指定当前用户的请假单
		List<LeaveBill> list = getSession().createQuery(hql).setParameter("employee", employee).list();
		return list;
	}
	
	/**保存请假单*/
	@Override
	public void saveLeaveBill(LeaveBill leaveBill) {
		getSession().save(leaveBill);
	}
	
	/**使用请假单ID，查询请假单的对象*/
	@Override
	public LeaveBill findLeaveBillById(Long id) {
		return (LeaveBill) getSession().get(LeaveBill.class, id);
	}
	
	/**更新请假单*/
	@Override
	public void updateLeaveBill(LeaveBill leaveBill) {
		getSession().update(leaveBill);
	}
	
	/**使用请假单ID，删除请假单*/
	@Override
	public void deleteLeaveBillById(Long id) {
		//2：使用请假单ID，查询请假单信息，获取对象LeaveBill
		LeaveBill bill = this.findLeaveBillById(id);
		//3：执行删除
		getSession().delete(bill);
	}
}
