package com.bank.dao.imp;

import com.aattijari.bank.entity.Interfaceelec;
import com.aattijari.bank.entity.Periheralelec;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import com.attijari.bank.technical.SessionSnmp;
import com.bank.dao.PIDAO;

import java.util.*;


/**
 *
 * @author PC
 */
public class PDAOelec implements PIDAO<Periheralelec> 
{
    private static final String Hostname = ".1.3.6.1.2.1.1.5.0";
    private static final String Description = ".1.3.6.1.2.1.1.1.0";
    private static final String SysUpTime = ".1.3.6.1.2.1.1.3.0";
    private static final String SysContact = ".1.3.6.1.2.1.1.4.0";
    private static final String SysLocation = ".1.3.6.1.2.1.1.6.0";
    private static final String Oid = ".1.3.6.1.2.1.1.2.0";
    private static final String IfNumber = ".1.3.6.1.2.1.2.1.0";
    private static final String IfDescr = ".1.3.6.1.2.1.2.2.1.2";
    private static final String IfType = ".1.3.6.1.2.1.2.2.1.3";
    private static final String IfOperStatus = ".1.3.6.1.2.1.2.2.1.7";
    private static final String ifName=".1.3.6.1.2.1.31.1.1.1.1";
    
    private static int NbInterface;
    

    /*************************************************************************************************************************/
    @Override
    public String SnmpGetHostname(String ipaddress) throws NullPointerException,Exception
    {
        SessionSnmp obj = new SessionSnmp();
        
        return obj.snmpGet(ipaddress, Hostname);
    }
    
    
    /*************************************************************************************************************************/

    @Override
    public String SnmpGetDescription(String ipaddress) throws NullPointerException,Exception
    {
        SessionSnmp obj = new SessionSnmp();
        
        return obj.snmpGet(ipaddress, Description);
    }

    /************************************************************************************************************************/
    
    public String SnmpGetOID(String ipaddress) throws NullPointerException,Exception
    {
        SessionSnmp obj = new SessionSnmp();
        
        return obj.snmpGet(ipaddress, Oid);
    }
/*****************************************************************************************************************************/
    
    public String SnmpGetSysUpTime(String ipaddress) throws NullPointerException, Exception
    {
         SessionSnmp obj = new SessionSnmp();
        
        return obj.snmpGet(ipaddress, SysUpTime);
    }
    
/****************************************************************************************************************************/
    
    public String SnmpGetSysContact(String ipaddress) throws NullPointerException,Exception
    {
        SessionSnmp obj = new SessionSnmp();
        
        return obj.snmpGet(ipaddress, SysContact);
    }
    
    /************************************************************************************************************************/
    
    public String SnmpGetSysLocation(String ipaddress) throws NullPointerException, Exception
    {
        SessionSnmp obj = new SessionSnmp();
        
        return obj.snmpGet(ipaddress, SysLocation);
    }
    
     /**********************************************************************************************************************************************/
    
    public void ModifyHostName(String Ipaddress, String newValue) throws NullPointerException, Exception
    {
      SessionSnmp obj = new SessionSnmp();
      
         obj.snmpSet(Ipaddress, Hostname, newValue);
      
    }
    
    
    
    /********************************************************************************************************************************************/
    
    public void ModifyContact(String Ipaddress, String value) throws NullPointerException,Exception
     {
        SessionSnmp obj = new SessionSnmp();
      
     obj.snmpSet(Ipaddress, SysContact, value);
    }

/************************************************************************************************************************************************/
     
    
    public void ModifyLocation(String Ipaddress, String value) throws NullPointerException, Exception
     {
        SessionSnmp obj = new SessionSnmp();
      
        obj.snmpSet(Ipaddress, SysLocation, value);
    }

    
    /************************************************************************************************************************/
    
    public List SnmpGetEachInterfaceID(String ipaddress) throws  NullPointerException,Exception
    {
        SessionSnmp obj = new SessionSnmp();
        String str="";
        String res = "";
        String first = IfNumber;
        String nextOid = "";
        List vect = new ArrayList();
        
        while (true)
        {
            str = obj.snmpGetNext(ipaddress, first);      
            StringTokenizer stk = new StringTokenizer(str," ");
                    
            nextOid = stk.nextToken(); // on récupère l'oid suivant
            
            if(nextOid.equals("1.3.6.1.2.1.2.2.1.2.1"))
            {
                break;
            }
            else
            {
                stk.nextToken(); // on ignore le caractère " = "
                res = stk.nextToken(); // Récupération de la valeur
                vect.add(res);
                first = nextOid;
            }
        }
       
        return vect;
    }
    
    /************************************************************************************************************************/
    
    public int SnmpGetNumberOfInterface(String ipaddress) throws NullPointerException,Exception
// nbre d'interface
    {
        SessionSnmp obj = new SessionSnmp();
        String str="";
        String res = "";
        String nextOid = "";
        String first = IfNumber;
        NbInterface = 0;
        
        while(true)
        { 
            str = obj.snmpGetNext(ipaddress, first); 
            StringTokenizer stk = new StringTokenizer(str," ");
            
             nextOid = stk.nextToken(); // on récupère l'oid suivant
            
            if(nextOid.equals("1.3.6.1.2.1.2.2.1.2.1"))
            { 
                break;
            }
            else
            {
                NbInterface++;
                first = nextOid;
            }
            
        }
        return NbInterface;
    }
    
    /************************************************************************************************************************/
    
     public List SnmpGetEachInterfaceDescr(String ipaddress) throws NullPointerException,Exception
// description de chaq interface
    {
        SessionSnmp obj = new SessionSnmp();
        String str="";
        String res = "";
        String first = IfDescr;
        String nextOid = "";
        List vect = new ArrayList();
        
        while (true)
        {
            str = obj.snmpGetNext(ipaddress, first);                        
            StringTokenizer stk = new StringTokenizer(str," ");
                    
            nextOid = stk.nextToken(); // on récupère l'oid suivant
            
            if(nextOid.equals("1.3.6.1.2.1.2.2.1.3.1"))
            {
                break;
            }
            else
            {
                stk.nextToken(); // on ignore le caractère " = "
            
            while(stk.hasMoreTokens())
            {
                res = res + stk.nextToken()+" ";
            }
            vect.add(res); // ajout ds la table du nom du logiciel
            first = nextOid;
            res = "";
            }
        }
        
        return vect;
    }
    
    /************************************************************************************************************************/
     
      public List SnmpGetEachInterfaceType(String ipaddress) throws NullPointerException,Exception
//type de l'interface
    {
        SessionSnmp obj = new SessionSnmp();
        String str="";
        String res = "";
        String first = IfType;
        String nextOid = "";
        List vect = new ArrayList();
        
        while (true)
        {
            str = obj.snmpGetNext(ipaddress, first);                        
            StringTokenizer stk = new StringTokenizer(str," ");
                    
            nextOid = stk.nextToken(); // on récupère l'oid suivant
            
            if(nextOid.equals("1.3.6.1.2.1.2.2.1.4.1"))
            {
                
                break;
            }
            else
            {
                stk.nextToken(); // on ignore le caractère " = "
            
                res = stk.nextToken(); // Récupération de la valeur
                vect.add(res);
                first = nextOid;
            }
        }
        
        return vect;
    }
     
     
     /************************************************************************************************************************/
     
        public List SnmpGetEachInterfaceName(String ipaddress) throws NullPointerException,Exception
               //etat de chaque interface
    {
        SessionSnmp obj = new SessionSnmp();
        String str="";
        String res = "";
        String first = ifName;
        String nextOid = "";
        List vect = new ArrayList();
        int nbint = SnmpGetNumberOfInterface(ipaddress);
        int i =0;
        
        while (i < nbint)
        {
            str = obj.snmpGetNext(ipaddress, first);                        
            StringTokenizer stk = new StringTokenizer(str," ");
                    
            nextOid = stk.nextToken(); // on récupère l'oid suivant
            
                stk.nextToken(); // on ignore le caractère " = "
            
                res = stk.nextToken(); // Récupération de la valeur
                
                
                
                vect.add(res);
                first = nextOid;
                i++;
            }
        return vect;
        }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
       public List SnmpGetEachInterfaceStatus(String ipaddress) throws NullPointerException,Exception
               //etat de chaque interface
    {
        SessionSnmp obj = new SessionSnmp();
        String str="";
        String res = "";
        String first = IfOperStatus;
        String nextOid = "";
        List vect = new ArrayList();
        int nbint = SnmpGetNumberOfInterface(ipaddress);
        int i =0;
        
        while (i < nbint)
        {
            str = obj.snmpGetNext(ipaddress, first); 
          
            StringTokenizer stk = new StringTokenizer(str," ");
                    
            nextOid = stk.nextToken(); // on récupère l'oid suivant
            
                stk.nextToken(); // on ignore le caractère " = "
            
                res = stk.nextToken(); // Récupération de la valeur
                
                if(res.equals("1"))
                     res = "UP";
                else if (res.equals("2"))
                     res = "Down";
                else if (res.equals("3"))
                    res = "Testing";
                else if (res.equals("4"))
                    res = "Unknown";
                else if (res.equals("5"))
                    res = "Dormant";
                else if (res.equals("6"))
                    res = "Not present";
                else if (res.equals("7"))
                    res = "Lower Layer Down";
                
                vect.add(res);
                first = nextOid;
                i++;
//            }
        }
        
        return vect;
    }
      
      /***********************************************************************************************************************/
      
      
     @Override
    public List SnmpGetPeripheralDescr(String ipaddress) throws NullPointerException,Exception
//description du périphériq
     {
        List ids = new ArrayList();
        List descr = new ArrayList();
        List type = new ArrayList();
        List name = new ArrayList();
        List status = new ArrayList();
        String ida ;
        String idb ;
        String idc ;
        String idd ;
        String nn;
        int a;
        int b;
        
        List <Interfaceelec> description = new ArrayList();
        
        ids = SnmpGetEachInterfaceID(ipaddress);
        descr = SnmpGetEachInterfaceDescr(ipaddress);
        type = SnmpGetEachInterfaceType(ipaddress);
        name = SnmpGetEachInterfaceName(ipaddress);
        status = SnmpGetEachInterfaceStatus(ipaddress);
        
        Iterator i = ids.iterator();
        Iterator j = descr.iterator();
        Iterator k = type.iterator();
        Iterator l = status.iterator();
        Iterator s = name.iterator();
        while (i.hasNext() && j.hasNext() && k.hasNext() && l.hasNext() && s.hasNext())
        {
            ida = (String) i.next();
            a = Integer.parseInt(ida);
            idb = (String) j.next();
            idc = (String) k.next();
            b = Integer.parseInt(idc);
            idd = (String) l.next();
            nn = (String) s.next();
            Interfaceelec inter = new Interfaceelec(a, b, nn,idb, b, idd);
            
            description.add(inter);
        }
        
       return description;
    }  

    @Override
    public String SnmpSetEachInterfaceStatus(String ipAdress,int val,int val2) throws Exception {
        
          SessionSnmp obj = new SessionSnmp();
       
        String first = IfOperStatus+"."+val2;
         return obj.snmpSet(ipAdress,first , val);
     
       
    }
   /**************************************************************************************************************/
     
    
//     public static void main(String[] args) 
//    {
//        PDAO cd = new PDAO();
//        int st;
//       //List st = new ArrayList();
//       //String st;
//        
//       st = cd.SnmpGetNumberOfInterface("192.168.2.1");
//        System.out.println(st);
//      
//    }

   
}
