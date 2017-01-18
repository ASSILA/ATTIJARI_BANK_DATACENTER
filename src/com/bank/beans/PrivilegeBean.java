package com.bank.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.hibernate.criterion.Criterion;

import com.aattijari.bank.entity.Historique;
import com.aattijari.bank.entity.Privilege;
import com.bank.dao.HistriqueDao;
import com.bank.dao.PrivilegeDao;
import com.bank.dao.imp.HistoriqueDaoImp;
import com.bank.dao.imp.PrivilegeDaoImp;




@ManagedBean
@SessionScoped
public class PrivilegeBean {
	private List<Privilege> listers=new ArrayList<Privilege>();;
	//@ManagedProperty(value ="#{privilegeService}")
	//private PrivilegeService privilegeServices;
	//private Privilege privilege=new Privilege();
	//private List<Privilege> listers;
//	@ManagedProperty(value ="#{privilegeService}")
//	private PrivilegeService privilegeServices;
	private Privilege privilege=new Privilege();
	
	private PrivilegeDao privilegeServices=new PrivilegeDaoImp();
	
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	
	
	
	public void setPrivilegeServices(PrivilegeDao privilegeServices) {
		this.privilegeServices = privilegeServices;
	}
	public List<Privilege> getListers() {
		try {
			listers = privilegeServices.findAll(Privilege.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listers;
	}
	public void setListers(List<Privilege> listers) {
		this.listers = listers;
	}

	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	public PrivilegeBean() {
		// TODO Auto-generated constructor stub
	}
	public void ajout()
	{
		try {
			long id1 = privilege.getIdprivilege();
			privilege.setIdprivilege(id1+1);
			privilegeServices.save(privilege);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
//			long id = historique.getId();
//			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter privilege");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
			privilegeServices.delete(privilege);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer privilege");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Privilege> lister(){
		 
		 try {
			listers = privilegeServices.findAll(Privilege.class);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter les privileges");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
	public List<SelectItem> getAllprivilege() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		try {
			List<Privilege> priv  = privilegeServices.findAll(Privilege.class);
			for (Privilege proj1 : priv) {
				if(proj1.getNom()!=null)
				{
				items.add(new SelectItem(proj1.getNom()));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
}
}
