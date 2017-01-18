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
import com.aattijari.bank.entity.Equipementinformatique;
import com.aattijari.bank.entity.Historique;
import com.bank.dao.ArmoireDao;
import com.bank.dao.EquipementInformatiqueDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.ArmoireDaoImp;
import com.bank.dao.imp.EquipementinformatiqueImp;
import com.bank.dao.imp.HistoriqueDaoImp;





@ManagedBean
@SessionScoped
public class EquipementinformatiqueBean {
//	private List<Equipementinformatique> listers;
//	@ManagedProperty(value ="#{equipementinfService}")
	//private EquipementInformatiqueService equipementInformatiqueServices;
//	private Equipementinformatique equipementinformatique =new Equipementinformatique();
	private List<Equipementinformatique> listers=new ArrayList<Equipementinformatique>();
//	@ManagedProperty(value ="#{equipementinfService}")
//	private EquipementInformatiqueService equipementInformatiqueServices;
	private Equipementinformatique equipementinformatique =new Equipementinformatique();
	private EquipementInformatiqueDao equipementInformatiqueServices=new EquipementinformatiqueImp();
	String nomarmoire;
    private ArmoireDao armoireService=new ArmoireDaoImp() ;
    private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	public EquipementInformatiqueDao getEquipementInformatiqueServices() {
		return equipementInformatiqueServices;
	}




	public void setEquipementInformatiqueServices(EquipementInformatiqueDao equipementInformatiqueServices) {
		this.equipementInformatiqueServices = equipementInformatiqueServices;
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




	public EquipementinformatiqueBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public List<Equipementinformatique> getListers() {
		
		try {
			listers = equipementInformatiqueServices.findAll(Equipementinformatique.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listers;
	}




	public void setListers(List<Equipementinformatique> listers) {
		this.listers = listers;
	}









	







	public Equipementinformatique getEquipementinformatique() {
		return equipementinformatique;
	}




	public void setEquipementinformatique(Equipementinformatique equipementinformatique) {
		this.equipementinformatique = equipementinformatique;
	}




	public void ajout()
	{
		try {
           
			if(equipementInformatiqueServices!=null)
			{
				if(armoireService!=null)
				{
				 Criterion cri=Restrictions.eq("nom", nomarmoire);
			List<Armoire> list1 = armoireService.findByCriteria(Armoire.class, cri);
			equipementinformatique.setArmoire(list1.get(0));
			equipementInformatiqueServices.save(equipementinformatique);
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
			
//			long id1 = equipementinformatique.getId();
//			equipementinformatique.setId(id1+1);
//			
			equipementInformatiqueServices.delete(equipementinformatique);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer equipement informatique");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Equipementinformatique> lister(){
		 
		 try {
			listers =equipementInformatiqueServices.findAll(Equipementinformatique.class);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter les equipements informatiques");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
}
