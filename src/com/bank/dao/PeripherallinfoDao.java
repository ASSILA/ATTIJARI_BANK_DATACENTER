package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.aattijari.bank.entity.Peripheralinfo;

public interface PeripherallinfoDao {

	public void AjoutBD(Peripheralinfo e) throws SQLException,Exception;
    public List<Peripheralinfo> findByEquipment(int idEquip) throws SQLException,Exception;
    public List<Peripheralinfo> ListerBD()  throws SQLException,Exception;
    public void updatePeripheral (Peripheralinfo p) throws SQLException,Exception;

}
