package com.test;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aattijari.bank.entity.Groupe;
import com.aattijari.bank.entity.Privilege;


import com.bank.dao.UtilisateurDao;
import com.bank.dao.imp.GroupeDaoImp;
import com.bank.dao.imp.UtilisateurDaoImp;




public class Test {
    
    public static void main(String...args) throws Exception
    {
//    	PrivilegeDaoImp privilegeServices=new PrivilegeDaoImp();
//Privilege p=new Privilege();
//Criterion criterion=Restrictions.eq("nom", "admin1");
//List<Privilege>  privileges=privilegeServices.findByCriteria(Privilege.class, criterion);
//for(Privilege pri:privileges)
//{
//	System.out.println(pri.getNom()+" "+pri.getAccessprivilege()+" "+pri.getId());
//}
////List<Privilege> privileges=privilegeServices.findAll(Privilege.class);
//// for(Privilege pri:privileges)
//// {
////	 
////	 System.out.println(pri.getNom());
////	 
//// }
////p.setNom("admin1");
////p.setAccessprivilege("gestion datacenter");
//
////try {
////privilegeServices.save(p);
////} catch (Exception e) {
////	// TODO Auto-generated catch block
////	e.printStackTrace();
////}  
//// 
//    	Groupe g=new Groupe();
//    	GroupeDaoImp groupesServices=new GroupeDaoImp();
//    	Criterion criterion=Restrictions.eq("nom", "a");
//    	List<Groupe>groupes=groupesServices.findAll(Groupe.class);
//    	List<Groupe>groupess=groupesServices.findByCriteria(Groupe.class, criterion);
//    	for(Groupe group:groupes)
//    	{
//    		System.out.println(group.getNom());
//    		Criterion criterion1=Restrictions.eq("id", group.getIdg());
//    		List<Groupe>groupess1=groupesServices.findByCriteria(Groupe.class, criterion1);
//           
//    		
//    	}
    
//    	 Utilisateur utilisateur=new Utilisateur() ;
//    	 UtilisateurDao utilisateurdao=new UtilisateurDaoImp() ;
//    	
//    	 List<Utilisateur> listers ;
//    	 List<Utilisateur> u;
//    	 List<Utilisateur> listUtilSelect; 
//    	 
//    	u=utilisateurdao.findAll(Utilisateur.class);
//    	 utilisateur.setNom("hamdi1");
//    
//    	 utilisateurdao.save(utilisateur);
//    	 
//    	 listers = utilisateurdao.findAll(Utilisateur.class);
//    	 
//			for (int i=0;i<listers.size();i++){
//				System.out.println(listers.get(i).getNom());
//			}
//			for(int i=0;i<u.size();i++)
//			{
//				Criterion criterion=Restrictions.eq("nom", u.get(i));
//				try {
//					listUtilSelect=utilisateurdao.findByCriteria(Utilisateur.class, criterion);
//					utilisateurdao.save(listUtilSelect.get(0));
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
    
    
//
//        ApplicationContext  ctx=new  ClassPathXmlApplicationContext("com/bank/util/applicationContext.xml");
//        
//       
//        PrivilegeService privilegeServices= (PrivilegeService) ctx.getBean("privilegeService");
//        List<Privilege> listers ;
//        listers =  privilegeServices.findAll();
//        
//			for (int i=0;i<listers.size();i++){
//				System.out.println(listers.get(i).getNom());
//			}
//    
//       Privilege p=new Privilege();
//        p.setNom("admin1");
//   	p.setAccessprivilege("parametres");
//   	privilegeServices.save(p);
    }
}
