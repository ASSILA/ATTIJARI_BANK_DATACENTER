package com.bank.dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

/**
 *
 * @author PC
 */
public interface PIDAO <E>
{
    public String SnmpGetHostname(String ipaddress) throws NullPointerException, Exception;
    public String SnmpGetDescription(String ipaddress) throws NullPointerException,Exception;
    public List SnmpGetPeripheralDescr(String ipaddress) throws NullPointerException,Exception;
    public String SnmpSetEachInterfaceStatus(String ipAdress,int val,int val2) throws Exception;
    
}
