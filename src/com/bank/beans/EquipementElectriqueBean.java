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
import com.aattijari.bank.entity.Equipementelectrique;
import com.aattijari.bank.entity.Historique;
import com.bank.dao.ArmoireDao;
import com.bank.dao.EquipementElectrique;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.ArmoireDaoImp;
import com.bank.dao.imp.EquipementElectriqueDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;




@ManagedBean
@SessionScoped
public class EquipementElectriqueBean {
	//private List<Equipementelectrique> listers;
//	private List<Equipementelectrique>lister;
//	@ManagedProperty(value ="#{equipementelecService}")
//	private EquipementElectriqueService equipementElectriqueServices;
//	private Equipementelectrique equipementelectrique =new Equipementelectrique();
  
	private List<Equipementelectrique>lister=new ArrayList<Equipementelectrique>();
//	@ManagedProperty(value ="#{equipementelecService}")
//	private EquipementElectriqueService equipementElectriqueServices;
	private Equipementelectrique equipementelectrique =new Equipementelectrique();
	private EquipementElectrique equipementElectriqueServices=new EquipementElectriqueDaoImp();
	String nomarmoire;
    private ArmoireDao armoireService=new ArmoireDaoImp() ;
    private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
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




	public void setEquipementElectriqueServices(EquipementElectrique equipementElectriqueServices) {
		this.equipementElectriqueServices = equipementElectriqueServices;
	}




	public EquipementElectriqueBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	public List<Equipementelectrique> getLister() {
		return lister;
	}




	public void setLister(List<Equipementelectrique> lister) {
		this.lister = lister;
	}



	public List<Equipementelectrique> getListers() {
		
		try {
			lister = equipementElectriqueServices.findAll(Equipementelectrique.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lister;
	}


	public Equipementelectrique getEquipementelectrique() {
		return equipementelectrique;
	}




	public void setEquipementelectrique(Equipementelectrique equipementelectrique) {
		this.equipementelectrique = equipementelectrique;
	}





	public void ajout()
	{
		try {
           
			if(equipementElectriqueServices!=null)
			{
				if(armoireService!=null)
				{
				 Criterion cri=Restrictions.eq("nom", nomarmoire);
			List<Armoire> list1 = armoireService.findByCriteria(Armoire.class, cri);
			equipementelectrique.setArmoire(list1.get(0));
			equipementElectriqueServices.save(equipementelectrique);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter equipement informatique");
			historiqueServices.save(historique);
			
			}
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
			equipementElectriqueServices.delete(equipementelectrique);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer equipement electrique");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Equipementelectrique> lister(){
		 
		 try {
			lister = equipementElectriqueServices.findAll(Equipementelectrique.class);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter les equipement electrique");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return lister;
	 }
}
