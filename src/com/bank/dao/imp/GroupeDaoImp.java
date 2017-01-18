package com.bank.dao.imp;

import org.hibernate.Criteria;

import com.aattijari.bank.entity.Groupe;
import com.bank.dao.GroupeDao;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dao.GenericDao;
import com.bank.util.HibernateUtil;



public class GroupeDaoImp extends GenericDaoImp implements GroupeDao{
//	private Session hibernateSession;
//	private Transaction transaction;
//	//le debut de l'operation
//   protected void sataroperation()
//    { 
//	   //ouvrir connection
//	   hibernateSession=HibernateUtil.currentSession();
//	   
//	   transaction=hibernateSession.beginTransaction();
//	   }
// //la fin de l'operation
//   protected void endoperation()
//   {
//	   
//	   transaction.commit();
//	   HibernateUtil.closeSession();
//   }
//	@Override
	
//	public	Groupe getGroupeByName(String name)throws Exception{
//		sataroperation();
//		
//			try{
//			Groupe gr= (Groupe) hibernateSession.createQuery("from Groupe gr where gr.NOM=:grName").setString("grName", name).uniqueResult();
//			endoperation();
//			return gr;
//			}
//			catch(Exception e ){
//			
//		return null;
//			}
//	}
	
}
