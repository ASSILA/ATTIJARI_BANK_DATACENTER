package com.bank.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.hibernate.criterion.Criterion;

import com.aattijari.bank.entity.Historique;

import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.HistoriqueDaoImp;





@ManagedBean
@SessionScoped
public class HistroriqueBean {
	private List<Historique> listers=new ArrayList<Historique>();;
	//@ManagedProperty(value ="#{historiqueService}")
	//private HistoriqueService historiqueServices;
	//private Historique historique =new Historique() ;
	
	//private List<Historique> listers;
//	@ManagedProperty(value ="#{historiqueService}")
//	private HistoriqueService historiqueServices;
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	
	
	
	

	public HistriqueDao getHistoriqueServices() {
		return historiqueServices;
	}
	public void setHistoriqueServices(HistriqueDao historiqueServices) {
		this.historiqueServices = historiqueServices;
	}
	public List<Historique> getListers() {
		try {
			listers = historiqueServices.findAll(Historique.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listers;
	}
	public void setListers(List<Historique> listers) {
		this.listers = listers;
	}

	public Historique getHistorique() {
		return historique;
	}
	public void setHistorique(Historique historique) {
		this.historique = historique;
	}
	public HistroriqueBean() {
		// TODO Auto-generated constructor stub
	}
	public void ajout()
	{
		try {
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
			historiqueServices.delete(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Historique> lister(){
		 
		 try {
			listers = historiqueServices.findAll(Historique.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
}
