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
import org.hibernate.criterion.Restrictions;

import com.aattijari.bank.entity.Groupe;
import com.aattijari.bank.entity.Historique;
import com.aattijari.bank.entity.Privilege;

import com.bank.dao.GroupeDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.PrivilegeDao;
import com.bank.dao.imp.GroupeDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;
import com.bank.dao.imp.PrivilegeDaoImp;



@ManagedBean
@SessionScoped
public class GroupeBean {
	private List<Groupe> listers=new ArrayList<Groupe>();;
	private List<Groupe> groupes;
//	@ManagedProperty(value ="#{groupeService}")
//	private GroupeService groupeServices;
//	private Groupe groupe =new Groupe();
	//
	//private List<Groupe> listers;
//	@ManagedProperty(value ="#{groupeService}")
//	private GroupeService groupeServices;
	private Groupe groupe =new Groupe();
	private GroupeDao groupeServices=new GroupeDaoImp();
	private String nomprivilege;
	private PrivilegeDao privilegeService=new PrivilegeDaoImp();
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	public String getNomprivilege() {
		return nomprivilege;
	}
	public void setNomprivilege(String nomprivilege) {
		this.nomprivilege = nomprivilege;
	}
	public PrivilegeDao getPrivilegeService() {
		return privilegeService;
	}
	public void setPrivilegeService(PrivilegeDao privilegeService) {
		this.privilegeService = privilegeService;
	}
	public GroupeDao getGroupeServices() {
		return groupeServices;
	}
	public void setGroupeServices(GroupeDao groupeServices) {
		this.groupeServices = groupeServices;
	}
	public List<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}
	
	public List<Groupe> getListers() {
		
		try {
			listers = groupeServices.findAll(Groupe.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listers;
	}
	public void setListers(List<Groupe> listers) {
		this.listers = listers;
	}
	
	public Groupe getGroupe() {
		return groupe;
	}
	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}
	public GroupeBean() {
		// TODO Auto-generated constructor stub
	}
	public void ajout()
	{
		try {
			
			Criterion cri=Restrictions.eq("nom", nomprivilege);
			
			List<Privilege> list1=privilegeService.findByCriteria(Privilege.class, cri);
		
			groupe.setPrivilege(list1.get(0));
			System.out.println(list1.get(0).getIdprivilege());
//			long id1 = groupe.getIdg();
//			groupe.setIdg(id1+1);
			groupeServices.save(groupe);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter groupe");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
			groupeServices.delete(groupe);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer groupe");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Groupe> lister(){
		 
		 try {
			 if(groupeServices!=null)
			 {
				 listers = groupeServices.findAll(Groupe.class);
				 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					long id = historique.getId();
					historique.setId(id+1);
					historique.setDatedevisite(date);
					historique.setUsers(UserSessionCtr.findByUserName());
					historique.setTacheeffect("consulter les groupes d'utilisateurs");
					historiqueServices.save(historique);
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
	public List<SelectItem> getAllgroupe() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		try {
			List<Groupe> groupe = groupeServices.findAll(Groupe.class);
			for (Groupe proj1 : groupe) {
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