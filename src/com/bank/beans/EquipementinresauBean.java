package com.bank.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.aattijari.bank.entity.Armoire;
import com.aattijari.bank.entity.Equipementreseau;
import com.aattijari.bank.entity.Historique;
import com.bank.dao.ArmoireDao;
import com.bank.dao.EquipementResauDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.ArmoireDaoImp;
import com.bank.dao.imp.EquipementReseauDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;






@ManagedBean
@SessionScoped
public class EquipementinresauBean {
	private List<Equipementreseau> listers=new ArrayList<Equipementreseau>();
	//@ManagedProperty(value ="#{equipementresService}")
	//private EquipementResauService equipementResauServices;
	//private Equipementreseau equipementreseau=new Equipementreseau();
	
//	private List<Equipementreseau> listers;;
//	@ManagedProperty(value ="#{equipementresService}")
//	private EquipementResauService equipementResauServices;
	private Equipementreseau equipementreseau=new Equipementreseau();
	private EquipementResauDao equipementResauServices=new EquipementReseauDaoImp();
	String nomarmoire;
    private ArmoireDao armoireService =new ArmoireDaoImp();
    private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	public EquipementResauDao getEquipementResauServices() {
		return equipementResauServices;
	}





	public void setEquipementResauServices(EquipementResauDao equipementResauServices) {
		this.equipementResauServices = equipementResauServices;
	}





	public String getNomarmoire() {
		return nomarmoire;
	}





	public void setNomarmoire(String nomarmoire) {
		this.nomarmoire = nomarmoire;
	}





	public ArmoireDao getArmoireService() {
		return armoireService;
	}





	public void setArmoireService(ArmoireDao armoireService) {
		this.armoireService = armoireService;
	}





	public EquipementinresauBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	


	public List<Equipementreseau> getListers() {
		
		try {
			listers = equipementResauServices.findAll(Equipementreseau.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listers;
	}


	public void setListers(List<Equipementreseau> listers) {
		this.listers = listers;
	}


	public Equipementreseau getEquipementreseau() {
		return equipementreseau;
	}


	public void setEquipementreseau(Equipementreseau equipementreseau) {
		this.equipementreseau = equipementreseau;
	}


	public void ajout()
	{
		try {
			if(equipementResauServices!=null)
			{
				if(armoireService!=null)
				{
             Criterion cri=Restrictions.eq("nom", nomarmoire);
			
			List<Armoire> list1 = armoireService.findByCriteria(Armoire.class, cri);
			equipementreseau.setArmoire(list1.get(0));
			equipementResauServices.save(equipementreseau);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter equipement resau");
			historiqueServices.save(historique);
		}
			}}
				
				catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
//			long id1 = equipementreseau.getId();
//			equipementreseau.setId(id1+1);
			equipementResauServices.delete(equipementreseau);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer equipement resau");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Equipementreseau> lister(){
		 
		 try {
			listers = equipementResauServices.findAll(Equipementreseau.class);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter les equipements resaux");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
}
