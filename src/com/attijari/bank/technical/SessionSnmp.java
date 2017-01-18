package com.attijari.bank.technical;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.snmp4j.*;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.*;
import org.snmp4j.transport.DefaultUdpTransportMapping;


public class SessionSnmp 
{
    public static final String HostAddress = "192.168.95.131";
    private static String communaute = "private";

    public SessionSnmp() 
    {
    }

    public static String getCommunaute() {
        return communaute;
    }

    public static void setCommunaute(String communaute) {
        SessionSnmp.communaute = communaute;
    }
    
    public static String snmpGet(String address, String OID) throws NullPointerException,Exception
    {
        String str = "";
        
            OctetString community = new OctetString(communaute);
            address = address + "/" + 161;
            Address targetaddress = new UdpAddress(address);
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            
            // setting up target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetaddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version1);
            ResponseEvent response;
            
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(OID)));
            pdu.setType(PDU.GET);
            Snmp snmp;
            snmp = new Snmp(transport);
            response = snmp.get(pdu, target);
            
           if (response != null) {
                if (response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
                    PDU pduresponse = response.getResponse();
                    str = pduresponse.getVariableBindings().firstElement().toString();
                    
                    if (str.contains("=")) {
                        int len = str.indexOf("=");
                        str = str.substring(len + 1, str.length());
                    }
                }
            } else {
                System.out.println("Feeling like a TimeOut occured ");
            }
            snmp.close();
            
            
       System.out.println(str);
       
        
        return str;
    
    }
    
 /**********************************************************************************************************/   
    
     public static String snmpGetNext(String address,  String OID) throws NullPointerException,Exception
    {
        String str = "";
        try {
            OctetString community = new OctetString(communaute);
            address = address + "/" + 161;
            Address targetaddress = new UdpAddress(address);
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            
            // setting up target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetaddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version1);
            ResponseEvent response;
   
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(OID)));
            pdu.setType(PDU.GETNEXT);
            Snmp snmp;
            snmp = new Snmp(transport);
            response = snmp.getNext(pdu, target);
            PDU pduresponse = response.getResponse();
            str = pduresponse.getVariableBindings().firstElement().toString();
           
            snmp.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        
        return str;
    
    }
    
 /*************************************************************************************************************************/
     
      public static String snmpSet(String address, String OID, int val) throws NullPointerException,Exception
    {
        String str = "";
        try {
            OctetString community = new OctetString(communaute);
            address = address + "/" + 161;
            Address targetaddress = new UdpAddress(address);
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            
            // setting up target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetaddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version1);
            //ResponseEvent response;
   
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(OID), new Integer32(val)));
            pdu.setType(PDU.SET);
            Snmp snmp;
            snmp = new Snmp(transport);
             
           
            ResponseListener listener = new ResponseListener() 
            {
           
            public void onResponse(ResponseEvent event) 
              {
                PDU strResponse;
                String result;
                ((Snmp)event.getSource()).cancel(event.getRequest(), this);
                strResponse = event.getResponse();
                
                if (strResponse!= null) 
                {
                    result = strResponse.getErrorStatusText();
                    System.out.println("Set Status is: "+result);
                }
              
              }
            
            };
            
             snmp.send(pdu, target, null, listener);
             snmp.close(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return str;
    
    }
     
        public static String snmpSet(String address, String OID, String val) throws NullPointerException,Exception
    {
        String str = "";
        try {
            OctetString community = new OctetString(communaute);
            address = address + "/" + 161;
            Address targetaddress = new UdpAddress(address);
            TransportMapping transport = new DefaultUdpTransportMapping();
            transport.listen();
            
            // setting up target
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetaddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(SnmpConstants.version1);
            //ResponseEvent response;
   
            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(OID), new OctetString(val)));
            pdu.setType(PDU.SET);
            Snmp snmp;
            snmp = new Snmp(transport);
             
           
            ResponseListener listener = new ResponseListener() 
            {
           
            public void onResponse(ResponseEvent event) 
              {
                PDU strResponse;
                String result;
                ((Snmp)event.getSource()).cancel(event.getRequest(), this);
                strResponse = event.getResponse();
                
                if (strResponse!= null) 
                {
                    result = strResponse.getErrorStatusText();
                    System.out.println("Set Status is: "+result);
                }
              
              }
            
            };
            
             snmp.send(pdu, target, null, listener);
             snmp.close(); 
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return str;
    
    }
     
 /************************************************************************************************************************/
      
    public static void main(String[] args) 
    {
        try {
            
            String strIPAddress = HostAddress;
            SessionSnmp obj = new SessionSnmp();
              obj.snmpGet(strIPAddress, ".1.3.6.1.2.1.2.2.1.2.1");
           //obj.snmpGet(HostAddress, ".1.3.6.1.2.1.25.2.3.1.6.6");
          // obj.snmpSet(HostAddress, ".1.3.6.1.2.1.1.4.0", "snmp@contact.com");
      // String  cv= obj.snmpSet("192.168.95.131", ".1.3.6.1.2.1.2.2.1.7.1",1);
       // System.out.println(cv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
}
