package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.aattijari.bank.entity.Peripheralres;

public interface PeripheralresDao {
	public void AjoutBD(Peripheralres e) throws SQLException,Exception;
    public List<Peripheralres> findByEquipment(int idEquip) throws SQLException,Exception;
    public List<Peripheralres> ListerBD()  throws SQLException,Exception;
    public void updatePeripheral (Peripheralres p) throws SQLException,Exception;
}
