package com.bank.beans;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.aattijari.bank.entity.Users;
import com.bank.dao.UtilisateurDao;
import com.bank.dao.imp.UtilisateurDaoImp;




@ManagedBean
@SessionScoped
public class UserSessionCtr {

	private Users user = new Users();


	public String getPrincipalName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails)
			return ((UserDetails) auth.getPrincipal()).getUsername();
		

		else
			return null;
	}


	public Users findByUserName(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth.isAuthenticated() && auth.getPrincipal() instanceof UserDetails){
			String userName=	((UserDetails) auth.getPrincipal()).getUsername();
			
			// AlertesDao aDao = new AlertesDaoImp();
			UtilisateurDao usersDao=new UtilisateurDaoImp();
			 
			 Criterion criterion = Restrictions.eq("username", userName);
			try {
			 //user=	(Users) aDao.findByCriteria(Users.class, criterion ).get(0);
			 user= (Users) usersDao.findByCriteria(Users.class, criterion).get(0);
			 
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
			 return user;
		}
		else{
			 return null;
		 }		
	}
	
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	
}
