package com.bank.dao.imp;import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dao.GenericDao;
import com.bank.util.HibernateUtil;



public class GenericDaoImp   implements GenericDao {

	private Session hibernateSession;
	private Transaction transaction;
	
	
	protected void startOperation() {
		
		hibernateSession = HibernateUtil.currentSession();
		transaction=hibernateSession.beginTransaction();
		
		
	}
	
	protected void endOperation(){
		
		transaction.commit();
		HibernateUtil.closeSession();
		
	}

	public void save(Object object) throws Exception {		
		startOperation();
		hibernateSession.merge(object);
		transaction.commit();
		
	}

	public void update(Object object) throws Exception {
		startOperation();
		hibernateSession.update(object);
		transaction.commit();
	}

	public void delete(Object object) throws Exception {
		startOperation();
		hibernateSession.delete(object);
		transaction.commit();
		
	}

	public List findAll(Class class1) throws Exception {
		startOperation();
		Criteria crit=hibernateSession.createCriteria(class1);
		
		transaction.commit();
		return crit.list();
		
	}

	public List findByCriteria(Class class1, Criterion criterion) throws Exception {
		startOperation();
		Criteria crit=hibernateSession.createCriteria(class1).add(criterion);
		transaction.commit();
		return crit.list();
		
	}
}
