package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.aattijari.bank.entity.Periheralelec;



public interface PeripheralelecDao {
	public void AjoutBD(Periheralelec e) throws SQLException,Exception;
    public List<Periheralelec > findByEquipment(int idEquip) throws SQLException,Exception;
    public List<Periheralelec > ListerBD()  throws SQLException,Exception;
    public void updatePeripheral (Periheralelec  p) throws SQLException,Exception;

}
