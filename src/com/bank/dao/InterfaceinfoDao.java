package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.aattijari.bank.entity.Interfaceinf;

public interface InterfaceinfoDao<E> {
	
	    public void AjoutBD(E e) throws SQLException,Exception;
	    public List ListerBD() throws SQLException,Exception;
	   
	    public void updateInterface(Interfaceinf interf) throws SQLException,Exception;
		List<Interfaceinf> findByPeripheral(String idPeripheral) throws SQLException, Exception;
	
}
