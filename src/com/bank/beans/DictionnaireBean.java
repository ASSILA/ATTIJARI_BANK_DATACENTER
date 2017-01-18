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
import com.aattijari.bank.entity.Dictionnaire;
import com.aattijari.bank.entity.Historique;
import com.bank.dao.DictionnaireDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.imp.DictionnaireDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;





@ManagedBean
@SessionScoped
public class DictionnaireBean {
//	private List<Dictionnaire> listers;
//	@ManagedProperty(value ="#{dictionnaireService}")
//	private DictionnaireService dictionnaireServices;
//	private Dictionnaire dictionnaire=new Dictionnaire() ;
	
	private List<Dictionnaire> listers =new ArrayList<Dictionnaire>();
//	@ManagedProperty(value ="#{dictionnaireService}")
//	private DictionnaireService dictionnaireServices;
	private Dictionnaire dictionnaire=new Dictionnaire() ;
	private DictionnaireDao dictionnaireServices=new DictionnaireDaoImp();
	
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
public DictionnaireDao getDictionnaireServices() {
		return dictionnaireServices;
	}
	public void setDictionnaireServices(DictionnaireDao dictionnaireServices) {
		this.dictionnaireServices = dictionnaireServices;
	}
public List<Dictionnaire> getListers() {
		
		try {
			listers = dictionnaireServices.findAll(Dictionnaire.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return listers;
		
	}
	public void setListers(List<Dictionnaire> listers) {
		this.listers = listers;
	}
	
	public void setDictionnaireService(DictionnaireDao dictionnaireService) {
		this.dictionnaireServices = dictionnaireServices;
	}
	public Dictionnaire getDictionnaire() {
		return dictionnaire;
	}
	public void setDictionnaire(Dictionnaire dictionnaire) {
		this.dictionnaire = dictionnaire;
	}
	public DictionnaireBean() {
		// TODO Auto-generated constructor stub
	}
	public void ajout()
	{
		try {
			dictionnaireServices.save(dictionnaire);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter dictionnaire");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void supprission()
	{
		try {
			dictionnaireServices.delete(dictionnaire);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer dictionnaire");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public List<Dictionnaire> lister(){
		 
		 try {
//			 long id1 = dictionnaire.getIddictionnaire();
//			 dictionnaire.setIddictionnaire(id1+1);
			listers = dictionnaireServices.findAll(Dictionnaire.class);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("consulter dictionnaire");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listers;
	 }
	public List<SelectItem> getAllregle() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		try {
			if(dictionnaireServices!=null)
			{
				
			List<Dictionnaire> dictionnaire = dictionnaireServices.findAll(Dictionnaire.class);
			for (Dictionnaire proj1 : dictionnaire) {
				if(proj1.getPakagesnmp()!=null)
				{
				items.add(new SelectItem(proj1.getPakagesnmp()));
				}
			}
		} 
		}
			catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return items;
}

}

