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

import com.aattijari.bank.entity.Interfaceelec;
import com.aattijari.bank.entity.Interfaceinf;
import com.aattijari.bank.entity.Interfaceres;
import com.bank.dao.InterfaceresDao;
import com.bank.util.HibernateUtil;

/**
 *
 * @author idri2sa
 */
public class InterfaceDAO implements InterfaceresDao <Interfaceres> 
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
    	Interfaceres p = new Interfaceres();
        List ls = new ArrayList();
        sataroperation();
		Class class1 = null;
		Criteria crit =hibernateSession.createCriteria(class1);
		endoperation();
		 return crit.list();
        
       
    }

    @Override
    public List<Interfaceres> findByPeripheral(String idPeripheral) throws SQLException,Exception {
    	Interfaceres p = new Interfaceres();
       Class interfaceelec = null;
      
           
           
       
            sataroperation();
			Criteria crit =hibernateSession.createCriteria(interfaceelec, idPeripheral);
    		endoperation();
    		return crit.list();
      
        
    }

       

	@Override
	public void AjoutBD(Interfaceres e) throws SQLException, Exception {
		sataroperation();
		//merge = ajout + modifier
		hibernateSession.merge(e);
		endoperation();
		
	}

	@Override
	public void updateInterface(Interfaceres interf) throws SQLException, Exception {
		// TODO Auto-generated method stub
		sataroperation();
		//merge = ajout + modifier
		hibernateSession.merge(interf);
		endoperation();
		
	}
}