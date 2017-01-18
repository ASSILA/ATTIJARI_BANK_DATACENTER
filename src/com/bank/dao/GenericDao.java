package com.bank.dao;
import java.util.List;
import org.hibernate.criterion.Criterion;
public interface GenericDao{

		public void save(Object o)  throws Exception;
		public void delete(Object o)  throws Exception;
		List findAll(Class cls)  throws Exception;
		public List findByCriteria(Class cls,Criterion criterion) throws Exception;

}

