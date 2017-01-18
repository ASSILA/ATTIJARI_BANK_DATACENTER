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

import com.aattijari.bank.entity.Armoire;
import com.aattijari.bank.entity.Datacenter;
import com.aattijari.bank.entity.Historique;
import com.bank.dao.ArmoireDao;
import com.bank.dao.DatacenterDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.ArmoireDaoImp;
import com.bank.dao.imp.DatacenterDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;







@ManagedBean
@SessionScoped
public class ArmoireBean {
	//private List<Armoire> listers;
//	@ManagedProperty(value ="#{armoireService}")
//	private ArmoireService armoireServices;
//	
	private List<Armoire> listers;
	//@ManagedProperty(value ="#{armoireService}")
	//private ArmoireService armoireServices;
	private ArmoireDao armoireServices=new ArmoireDaoImp();
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	//private Armoire armoire=new Armoire();
		
	private Armoire armoire=new Armoire();
	String nomdatacenter;
	
	private DatacenterDao datacenterservice=new DatacenterDaoImp();
	
	public String getNomdatacenter() {
		return nomdatacenter;
	}



	public void setNomdatacenter(String nomdatacenter) {
		this.nomdatacenter = nomdatacenter;
	}



	public DatacenterDao getDatacenterservice() {
		return datacenterservice;
	}



	public void setDatacenterservice(DatacenterDao datacenterservice) {
		this.datacenterservice = datacenterservice;
	}



	public void setArmoireServices(ArmoireDao armoireServices) {
		this.armoireServices = armoireServices;
	}



	public ArmoireBean() {
		// TODO Auto-generated constructor stub
	}
	
	

	public List<Armoire> getListers() {
		try {
			if(armoireServices!=null)
			{
			listers = armoireServices.findAll(Armoire.class);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listers;
	}
	public void setListers(List<Armoire> listers) {
		this.listers = listers;
	}
	
	public Armoire getArmoire() {
		return armoire;
	}
	public void setArmoire(Armoire armoire) {
		this.armoire = armoire;
	}
	
	public void ajout()
	{
		try {
			if(armoireServices!=null)
			{
			if(datacenterservice!=null)
			{
			Criterion cri=Restrictions.eq("nom", nomdatacenter);
			
			
			List<Datacenter> list1 = datacenterservice.findByCriteria(Datacenter.class, cri);
			armoire.setDatacenter(list1.get(0));
//			long id1 = armoire.getIdarmoire();
//			armoire.setIdarmoire(id1+1);
			System.out.println(list1.get(0).getIddata());
			
			
			armoireServices.save(armoire);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter armoire");
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
			armoireServices.delete(armoire);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer armoire");
			historiqueServices.save(historique);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Armoire> lister(){
		 
		 try {
			listers = armoireServices.findAll(Armoire.class);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter armoire");
			historiqueServices.save(historique);
		 
		 } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
	public List<SelectItem> getAllarmoire() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		try { if(armoireServices!=null)
		{
			List<Armoire> armoire = armoireServices.findAll(Armoire.class);
			for (Armoire proj1 : armoire) {
				if(proj1.getNom()!=null)
				{
				items.add(new SelectItem(proj1.getNom()));
				}
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
}
}
