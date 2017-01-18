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

import com.aattijari.bank.entity.Dictionnaire;
import com.aattijari.bank.entity.Groupe;
import com.aattijari.bank.entity.Historique;
import com.aattijari.bank.entity.Regle;

import com.bank.dao.DictionnaireDao;
import com.bank.dao.GroupeDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.RegleDao;
import com.bank.dao.imp.DictionnaireDaoImp;
import com.bank.dao.imp.GroupeDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;
import com.bank.dao.imp.RegleDaoImp;


@ManagedBean
@SessionScoped
public class RegleBean {

	private RegleDao regleServices = new RegleDaoImp();
	private List<Regle> listers=new ArrayList<Regle>();
	// @ManagedProperty(value ="#{regleService}")
	// private RegleService regleServices;
	private Regle regle = new Regle();

	private String nomgroupe;
	private String type;

	private DictionnaireDao dictionnaireService=new  DictionnaireDaoImp();

	private GroupeDao groupeService=new GroupeDaoImp();
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		

	public String getNomgroupe() {
		return nomgroupe;
	}

	public void setNomgroupe(String nomgroupe) {
		this.nomgroupe = nomgroupe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public DictionnaireDao getDictionnaireService() {
		return dictionnaireService;
	}

	public void setDictionnaireService(DictionnaireDao dictionnaireService) {
		this.dictionnaireService = dictionnaireService;
	}

	public GroupeDao getGroupeService() {
		return groupeService;
	}

	public void setGroupeService(GroupeDao groupeService) {
		this.groupeService = groupeService;
	}

	public void setRegleServices(RegleDao regleServices) {
		this.regleServices = regleServices;
	}

	public List<Regle> getListers() {

		try {
			listers = regleServices.findAll(Regle.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listers;
	}

	public void setListers(List<Regle> listers) {
		this.listers = listers;
	}


	public Regle getRegle() {
		return regle;
	}

	public void setRegle(Regle regle) {
		this.regle = regle;
	}

	public RegleBean() {
		// TODO Auto-generated constructor stub
	}

	public void ajout() {
		try {
			if(regleServices!=null)
			{
				if(dictionnaireService!=null)
				{
			if(regleServices!=null)
			{
			Criterion cri=Restrictions.eq("nom", nomgroupe);
			Criterion cri1=Restrictions.eq("pakagesnmp", type);
			List<Dictionnaire> list2 = dictionnaireService.findByCriteria(Dictionnaire.class, cri1);
			List<Groupe> list1 = groupeService.findByCriteria(Groupe.class, cri);
			regle.setGroupe(list1.get(0));
			regle.setDictionnaire(list2.get(0));
//			long id1 = regle.getId();
//			regle.setId(id1+1);
			regleServices.save(regle);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("ajouter regle");
			historiqueServices.save(historique);
		}
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprission() {
		try {
			regleServices.delete(regle);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer regle");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Regle> lister() {

		try {
			listers = regleServices.findAll(Regle.class);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			long id = historique.getId();
			historique.setId(id+1);
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect(" consulter les regles");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listers;
	}
}
