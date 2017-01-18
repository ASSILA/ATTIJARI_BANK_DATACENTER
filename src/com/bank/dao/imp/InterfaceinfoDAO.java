package com.bank.dao.imp;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bank.dao.GenericDao;
import com.bank.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aattijari.bank.entity.Interfaceinf;
import com.aattijari.bank.entity.Interfaceres;
import com.bank.dao.InterfaceinfoDao;
import com.bank.util.HibernateUtil;

/**
 *
 * @author idri2sa
 */
public class InterfaceinfoDAO implements InterfaceinfoDao<Interfaceinf>  
{

	private Session hibernateSession;
	private Transaction transaction;
	//le debut de l'operation
   protected void sataroperation()
    { 
	   //ouvrir connection
	   hibernateSession=HibernateUtil.currentSession();
	   
	   transaction=hibernateSession.beginTransaction();
	   }
 //la fin de l'operation
   protected void endoperation()
   {
	   
	   transaction.commit();
	   HibernateUtil.closeSession();
   }
    

    @Override
    public List ListerBD() throws SQLException,Exception
    {
    	Interfaceinf p = new Interfaceinf();
        List ls = new ArrayList();
        sataroperation();
		Class class1 = null;
		Criteria crit =hibernateSession.createCriteria(class1);
		endoperation();
		 return crit.list();
        
       
    }

    @Override
    public List<Interfaceinf> findByPeripheral(String idPeripheral) throws SQLException,Exception {
    	Interfaceres p = new Interfaceres();
       Class interfaceelec = null;
      
           
           
       
            sataroperation();
			Criteria crit =hibernateSession.createCriteria(interfaceelec, idPeripheral);
    		endoperation();
    		return crit.list();
      
        
    }

       

	@Override
	public void AjoutBD(Interfaceinf e) throws SQLException, Exception {
		sataroperation();
		//merge = ajout + modifier
		hibernateSession.merge(e);
		endoperation();
		
	}

	@Override
	public void updateInterface(Interfaceinf interf) throws SQLException, Exception {
		// TODO Auto-generated method stub
		sataroperation();
		//merge = ajout + modifier
		hibernateSession.merge(interf);
		endoperation();
		
	}
}