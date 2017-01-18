package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.aattijari.bank.entity.Interfaceres;

public interface InterfaceresDao <E>{
	
	    public void AjoutBD(E e) throws SQLException,Exception;
	    public List ListerBD() throws SQLException,Exception;
	    
	    public void updateInterface(Interfaceres interf) throws SQLException,Exception;
		List<Interfaceres> findByPeripheral(String idPeripheral) throws SQLException, Exception;
	
}
