package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.criterion.Criterion;

import com.aattijari.bank.entity.Interfaceelec;

public interface InterfaceelecDao<E> {
	
	    public void AjoutBD(E e) throws SQLException,Exception;
	    public List ListerBD() throws SQLException,Exception;
	   
	    public void updateInterface(Interfaceelec interf) throws SQLException,Exception;
		
		List<Interfaceelec> findByPeripheral(String idPeripheral) throws SQLException, Exception;
	
}
