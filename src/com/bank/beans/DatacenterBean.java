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

import com.aattijari.bank.entity.Datacenter;
import com.aattijari.bank.entity.Historique;
import com.bank.dao.DatacenterDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.DatacenterDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;





@ManagedBean
@SessionScoped
public class DatacenterBean {

	private List<Datacenter> listers =new ArrayList<Datacenter>();

	private Datacenter datacenter= new Datacenter();
	
	private DatacenterDao datacenterServices = new DatacenterDaoImp() ;
	
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	
	
	public DatacenterDao getDatacenterServices() {
		return datacenterServices;
	}
	public void setDatacenterServices(DatacenterDao datacenterServices) {
		this.datacenterServices = datacenterServices;
	}
	public List<Datacenter> getListers() {
		
		try {
			listers = datacenterServices.findAll(Datacenter.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listers;
		
	}
	public DatacenterBean() {
		// TODO Auto-generated constructor stub
	}
	public void setListers(List<Datacenter> listers) {
		this.listers = listers;
	}

	public Datacenter getDatacenter() {
		return datacenter;
	}
	public void setDatacenter(Datacenter datacenter) {
		this.datacenter = datacenter;
	}
	public void ajout()
	{
		try {
//			long id1 = datacenter.getIddata();
//			datacenter.setIddata(id1+1);
			datacenterServices.save(datacenter);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter datacenter");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
			datacenterServices.delete(datacenter);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer datacenter");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Datacenter> lister(){
		 
		 try {
			listers = datacenterServices.findAll(Datacenter.class);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter datacenter");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
	public List<SelectItem> getAlldatacenter() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		try {
			List<Datacenter> datacenter = datacenterServices.findAll(Datacenter.class);
			for (Datacenter proj1 : datacenter) {
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
