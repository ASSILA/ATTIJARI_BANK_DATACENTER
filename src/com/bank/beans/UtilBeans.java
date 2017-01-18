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

import com.aattijari.bank.entity.Groupe;
import com.aattijari.bank.entity.Historique;
import com.aattijari.bank.entity.Users;
import com.bank.dao.GroupeDao;
import com.bank.dao.HistriqueDao;
import com.bank.dao.UtilisateurDao;
import com.bank.dao.imp.GroupeDaoImp;
import com.bank.dao.imp.HistoriqueDaoImp;
import com.bank.dao.imp.UtilisateurDaoImp;


@ManagedBean
@SessionScoped
public class UtilBeans {
	// @ManagedProperty(value ="#{UsersService}")

	private UtilisateurDao UsersServices = new UtilisateurDaoImp();
	private Users Users = new Users();
	private List<Users> listers = new ArrayList<Users>();

	GroupeDao groupeService = new GroupeDaoImp();
	String nomgroupe;
	private Historique historique =new Historique() ;
	private HistriqueDao historiqueServices=new HistoriqueDaoImp();
	UserSessionCtr UserSessionCtr=new UserSessionCtr();
		
	public void setGroupeService(GroupeDao groupeService) {
		this.groupeService = groupeService;
	}

	public UtilisateurDao getUsersServices() {
		return UsersServices;
	}

	public void setUsersServices(UtilisateurDao UsersServices) {
		this.UsersServices = UsersServices;
	}

	public String getNomgroupe() {
		return nomgroupe;
	}

	public void setNomgroupe(String nomgroupe) {
		this.nomgroupe = nomgroupe;
	}

	public Users getUsers() {
		return Users;
	}

	public void setUsers(Users Users) {
		this.Users = Users;
	}

	// public UsersDao getUsersdao() {
	// return Usersdao;
	// }
	//
	// public void setUsersdao(UsersDao Usersdao) {
	// this.Usersdao = Usersdao;
	// }

	public List<Users> getListers() {
		return listers;
	}

	public void setListers(List<Users> listers) {
		this.listers = listers;
	}

	public void ajouterUsers() {

		try {
			if (UsersServices != null) {
				if (groupeService != null) {
					Criterion cri = Restrictions.eq("nom", nomgroupe);
					List<Groupe> list1 = groupeService.findByCriteria(Groupe.class, cri);

					Users.setGroupe(list1.get(0));
//                    long id1 = Users.getId();
//                    Users.setId(id1);
					System.out.println(list1.get(0).getIdg());
					System.out.println("ajout");
					UsersServices.save(Users);
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
					long id = historique.getId();
					historique.setId(id+1);
					historique.setDatedevisite(date);
					historique.setUsers(UserSessionCtr.findByUserName());
					historique.setTacheeffect("ajouter utilisateur");
					historiqueServices.save(historique);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void supprimerUserss() {
		try {
			UsersServices.delete(Users);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			historique.setDatedevisite(date);
			historique.setUsers(UserSessionCtr.findByUserName());
			historique.setTacheeffect("supprimer utilisateur");
			historiqueServices.save(historique);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<Users> lister() {

		try {
			if (UsersServices != null) {
				listers = UsersServices.findAll(Users.class);
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				historique.setDatedevisite(date);
				historique.setUsers(UserSessionCtr.findByUserName());
				historique.setTacheeffect("consulter les utilisateurs");
				historiqueServices.save(historique);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listers;
	}

}
