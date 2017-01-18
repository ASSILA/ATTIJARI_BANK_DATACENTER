package com.bank.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

import com.aattijari.bank.entity.Groupe;
import com.aattijari.bank.entity.Historique;
import com.attijari.bank.technical.SNMP4JHelper;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.HistoriqueDaoImp;



@ManagedBean
@SessionScoped
public class SnmpBean {
	private List<SNMP4JHelper> listers=new ArrayList<SNMP4JHelper>();
	private SNMP4JHelper snmph=new SNMP4JHelper();
	String strIPAddress;
	String routerdescription ;
	String uptime ;
	String location ;
	String systeminitialloadparamtres ;
	String hrSystemNumUsers;
	String sysLocation;

	String sysServices ;
	String numberofrunningprocesses ;
	String hrSystemMaxProcesses ;
	String timethehoshasbeenrunningforSystemUptime;
	String SystemDate;
	String SystemInitialLoadDevice;
	public static final String READ_COMMUNITY = "public";
	public static final String WRITE_COMMUNITY= "private";
	public static final int mSNMPVersion =0; // 0 represents SNMP version=1
	public static final String OID_UPS_OUTLET_GROUP1 ="1.3.6.1.4.1.318.1.1.1.12.3.2.1.3.1";
	public static final String OID_UPS_BATTERY_CAPACITY="1.3.6.1.4.1.318.1.1.1.2.2.1.0";
	private List<String>  OID=new ArrayList<String>();
	public static final String SNMP_PORT="161";	
	private List<String> oids= Arrays.asList("routerdescription","uptime","location","systeminitialloadparamtres","hrSystemNumUsers","sysLocation","sysServices","numberofrunningprocesses","hrSystemMaxProcesses","timethehoshasbeenrunningforSystemUptime","SystemDate","SystemInitialLoadDevice" );
	private String oid;
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();

	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public List<String> getOID() {
		return OID;
	}


	public void setOID(List<String> oID) {
		OID = oID;
	}


	public String getStrIPAddress() {
		return strIPAddress;
	}


	public void setStrIPAddress(String strIPAddress) {
		this.strIPAddress = strIPAddress;
	}


	public List<String> getOids() {
		return oids;
	}


	public void setOids(List<String> oids) {
		this.oids = oids;
	}


	public List<SNMP4JHelper> getListers() {
			return listers;
		}


		public void setListers(List<SNMP4JHelper> listers) {
			this.listers = listers;
		}


		public SNMP4JHelper getSnmph() {
			return snmph;
		}


		public void setSnmph(SNMP4JHelper snmph) {
		
			
			this.snmph = snmph;
		}
		public List<SelectItem> getAlloid() {
			List<SelectItem> items = new ArrayList<SelectItem>();
			try {
				for (String oid1 : oids) {

					items.add(new SelectItem(oid1.toString()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return items;
	}

		 String Datos1;
		 String Datos2;
		 String Datos3;
		 
		public String getDatos1() {
			return Datos1;
		}


		public void setDatos1(String datos1) {
			Datos1 = datos1;
		}


		public String getDatos2() {
			return Datos2;
		}


		public void setDatos2(String datos2) {
			Datos2 = datos2;
		}


		public String getDatos3() {
			return Datos3;
		}


		public void setDatos3(String datos3) {
			Datos3 = datos3;
		}


		public void afficher(){
			if(oid.equals("routerdescription")){
				oid=routerdescription= "1.3.6.1.2.1.1.1.0";
			}
			if(oid.equals("uptime")){
				oid=uptime= "1.3.6.1.2.1.1.3.0";
			}
			if(oid.equals("location")){
				oid=location= "1.3.6.1.2.1.1.6.0";
			}
			if(oid.equals("systeminitialloadparamtres")){
				oid=systeminitialloadparamtres="1.3.6.1.2.1.25.1.4.0";
			}
			if(oid.equals("hrSystemNumUsers")){
				oid=hrSystemNumUsers="1.3.6.1.2.1.25.1.5.0";
			}
			if(oid.equals("sysLocation")){
				oid=sysLocation="1.3.6.1.2.1.1.6";
			}
			if(oid.equals("sysServices")){
				oid=sysServices="1.3.6.1.2.1.1.7";
			}
			if(oid.equals("numberofrunningprocesses")){
				oid=numberofrunningprocesses= "1.3.6.1.2.1.25.1.6.0";
			}
			if(oid.equals("hrSystemMaxProcesses")){
				oid=hrSystemMaxProcesses="1.3.6.1.2.1.25.1.7.0";
			}
			if(oid.equals("timethehoshasbeenrunningforSystemUptime")){
				oid=timethehoshasbeenrunningforSystemUptime=" 1.3.6.1.2.1.25.1.1.0";
			}
			if(oid.equals("SystemDate")){
				oid=SystemDate="1.3.6.1.2.1.25.1.2.0";
			}
			if(oid.equals("SystemInitialLoadDevice")){
				oid=SystemInitialLoadDevice="1.3.6.1.2.1.25.1.3.0";
			}
			
			
			    Datos1 =snmpGet(strIPAddress,READ_COMMUNITY,oid);
		        System.out.println(Datos1);
		        Datos2 =snmpGetNext(strIPAddress,READ_COMMUNITY,oid);
		        System.out.println(Datos2);
		        Datos3 =snmpGetNextOID(strIPAddress,READ_COMMUNITY,oid);
		        System.out.println(Datos3);
		        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				long id = historique.getId();
				historique.setId(id+1);
				historique.setDatedevisite(date);
				historique.setUsers(UserSessionCtr.findByUserName());
				historique.setTacheeffect("Verifier l'etat des équipement");
				try {
					historiqueServices.save(historique);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	/*  1.3.6.1.2.1.1.1.0  =>router description
		 * 1.3.6.1.2.1.1.3.0   =>uptime (in hundredths of a second)
		 * 1.3.6.1.2.1.1.6.0   =>location entry of this node
		 * 1.3.6.1.2.1.25.1.4.0 =>System Initial Load Parameters
		   1.3.6.1.2.1.25.1.5.0 =>hrSystemNumUsers
		   .1.3.6.1.2.1.1.5.0
		   1.3.6.1.2.1.1.6      =>sysLocation
		   1.3.6.1.2.1.1.7      =>sysServices
		   1.3.6.1.2.1.25.1.6.0  =>number of running processes
		   1.3.6.1.2.1.25.1.7.0  =>hrSystemMaxProcesses

		   1.3.6.1.2.1.25.1.1.0  =>time the host has been running for (SystemUptime) 
		   1.3.6.1.2.1.25.1.2.0   =>System Date
		   1.3.6.1.2.1.25.1.3.0  =>System Initial Load Device
		 * 
		 * */
	public SnmpBean() {
		// TODO Auto-generated constructor stub
	}

	public void snmpSet(String strAddress, String community, String strOID, int Value)
	{
	strAddress= strAddress+"/"+SNMP_PORT;
	Address targetAddress = GenericAddress.parse(strAddress);
	Snmp snmp;
	try
	{
	TransportMapping transport = new DefaultUdpTransportMapping();
	snmp = new Snmp(transport);
	transport.listen();
	CommunityTarget target = new CommunityTarget();
	target.setCommunity(new OctetString(community));
	target.setAddress(targetAddress);
	target.setRetries(2);
	target.setTimeout(5000);
	target.setVersion(SnmpConstants.version1);
	PDU pdu = new PDU();
	pdu.add(new VariableBinding(new OID(strOID), new Integer32(Value)));
	pdu.setType(PDU.SET);
	ResponseListener listener = new ResponseListener() {
	public void onResponse(ResponseEvent event) {
	// Always cancel async request when response has been received
	// otherwise a memory leak is created! Not canceling a request
	// immediately can be useful when sending a request to a broadcast
	// address.
	((Snmp)event.getSource()).cancel(event.getRequest(), this);
	System.out.println("Set Status is:"+event.getResponse().getErrorStatusText());
	}
	};
	snmp.send(pdu, target, null, listener);
	snmp.close();
	}
	catch (Exception e)
	{
	e.printStackTrace();
	}
	}
	/*
	* The code is valid only SNMP version1. SnmpGet method
	* return Response for given OID from the Device.
	*/
	public static String snmpGet(String strAddress, String community, String strOID) {

	    String str="";

	    try {
	        OctetString community2 = new OctetString(community);
	        strAddress= strAddress+"/"+SNMP_PORT;
	        System.out.println(strAddress);
	        Address targetaddress = new UdpAddress(strAddress);
	        TransportMapping transport = new DefaultUdpTransportMapping();
	        transport.listen();
	        CommunityTarget comtarget = new CommunityTarget();
	        comtarget.setCommunity(community2);
	        comtarget.setVersion(SnmpConstants.version2c);
	        comtarget.setAddress(targetaddress);
	        comtarget.setRetries(2);
	        comtarget.setTimeout(1000);
	        PDU pdu = new PDU();
	        ResponseEvent response;
	        Snmp snmp;
	        pdu.add(new VariableBinding(new OID(strOID)));
	        pdu.setType(PDU.GET);
	        snmp = new Snmp(transport);
	        response = snmp.get(pdu,comtarget);
	        System.out.println("----> "+response.getRequest().toString());
	        System.out.println("----> "+response.getResponse().toString());
	        if(response != null) {
	            if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
	                PDU pduresponse=response.getResponse();
	                str=pduresponse.getVariableBindings().firstElement().toString();
	                if(str.contains("=")) {
	                    int len = str.indexOf("=");
	                    str=str.substring(len+1, str.length());
	                }
	            }
	        } else {
	            System.out.println("Feeling like a TimeOut occured ");
	        }
	        
	         snmp.close();
	    } catch(Exception e) {
	        System.err.println(e.toString());
	    }

	    //System.out.println("Response="+str);
	    return str.trim();
	    }

	/*
	* The code is valid only SNMP version1. SnmpGet method
	* return Response for given OID from the Device.
	*/
	public static String snmpGetNext(String strAddress, String community, String strOID) {

	    String str="";

	    try {
	        OctetString community2 = new OctetString(community);
	        strAddress= strAddress+"/"+SNMP_PORT;
	        //System.out.println(strAddress);
	        Address targetaddress = new UdpAddress(strAddress);
	        TransportMapping transport = new DefaultUdpTransportMapping();
	        transport.listen();
	        CommunityTarget comtarget = new CommunityTarget();
	        comtarget.setCommunity(community2);
	        comtarget.setVersion(SnmpConstants.version2c);
	        comtarget.setAddress(targetaddress);
	        comtarget.setRetries(2);
	        comtarget.setTimeout(1000);

	        ResponseEvent response;
	        Snmp snmp;
	        //pdu.add(new VariableBinding(new OID(strOID)));
	        //pdu.setType(PDU.GETNEXT);

	        // Create the PDU object
	        PDU pdu = new PDU();
	        pdu.add(new VariableBinding(new OID(strOID))); //Querying GetNext of sysDescr will get the sysObjectID OID value
	        pdu.setRequestID(new Integer32(1));
	        pdu.setType(PDU.GETNEXT);


	        snmp = new Snmp(transport);
	        response = snmp.getNext(pdu,comtarget);
	        //System.out.println("----> "+response.getRequest().toString());
	        //System.out.println("----> "+response.getResponse().toString());
	        if(response != null) {
	            if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
	                PDU pduresponse=response.getResponse();
	                str=pduresponse.getVariableBindings().firstElement().toString();
	                if(str.contains("=")) {
	                    int len = str.indexOf("=");
	                    str=str.substring(len+1, str.length());
	                }
	            }
	        } else {
	            System.out.println("Feeling like a TimeOut occured ");
	        }
	        
	         snmp.close();
	    } catch(Exception e) {
	        System.err.println(e.toString());
	    }

	    //System.out.println("Response="+str);
	    return str.trim();
	    }

	public static String snmpGetNextOID(String strAddress, String community, String strOID) {

	    String str="";

	    try {
	        OctetString community2 = new OctetString(community);
	        strAddress= strAddress+"/"+SNMP_PORT;
	        //System.out.println(strAddress);
	        Address targetaddress = new UdpAddress(strAddress);
	        TransportMapping transport = new DefaultUdpTransportMapping();
	        transport.listen();
	        CommunityTarget comtarget = new CommunityTarget();
	        comtarget.setCommunity(community2);
	        comtarget.setVersion(SnmpConstants.version2c);
	        comtarget.setAddress(targetaddress);
	        comtarget.setRetries(2);
	        comtarget.setTimeout(1000);

	        ResponseEvent response;
	        Snmp snmp;
	        //pdu.add(new VariableBinding(new OID(strOID)));
	        //pdu.setType(PDU.GETNEXT);

	        // Create the PDU object
	        PDU pdu = new PDU();
	        pdu.add(new VariableBinding(new OID(strOID))); //Querying GetNext of sysDescr will get the sysObjectID OID value
	        pdu.setRequestID(new Integer32(1));
	        pdu.setType(PDU.GETNEXT);


	        snmp = new Snmp(transport);
	        response = snmp.getNext(pdu,comtarget);
	        //System.out.println("----> "+response.getRequest().toString());
	        //System.out.println("----> "+response.getResponse().toString());
	        if(response != null) {
	            if(response.getResponse().getErrorStatusText().equalsIgnoreCase("Success")) {
	                PDU responsePDU=response.getResponse();
	                if (responsePDU != null)  {
	                    int errorStatus = responsePDU.getErrorStatus();
	                    int errorIndex = responsePDU.getErrorIndex();
	                    String errorStatusText = responsePDU.getErrorStatusText();

	                    if (errorStatus == PDU.noError)  {
	                        //System.out.println("Snmp GetNext Response for sysObjectID = " + responsePDU.getVariableBindings());
	                        VariableBinding v= responsePDU.get(0);
	                        str=v.getOid().toString();

	                    }  else  {
	                        System.out.println("Error: Request Failed");
	                        System.out.println("Error Status = " + errorStatus);
	                        System.out.println("Error Index = " + errorIndex);
	                        System.out.println("Error Status Text = " + errorStatusText);
	                    }
	                }  else  {
	                    System.out.println("Error: GetNextResponse PDU is null");
	                }
	            }
	        } else {
	            System.out.println("Feeling like a TimeOut occured ");
	        }

	         snmp.close();
	    } catch(Exception e) {
	        System.err.println(e.toString());
	    }

	    //System.out.println("Response="+str);
	    return str.trim();
	    }



	
	
}
