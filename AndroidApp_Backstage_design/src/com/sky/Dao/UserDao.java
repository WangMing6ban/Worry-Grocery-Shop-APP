package com.sky.Dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.sky.Bean.User;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public boolean saveUser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction tran =session.beginTransaction();
		try {
			session.save(user);
			tran.commit();
			session.close();
			return true;
		}catch(Exception e) {
			System.out.print(e);
			tran.rollback();
			session.close();
			return false;
		}
	} 
	public User checkPhone(String user_phone) {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("select u from User u where u.user_phone=?");
		query.setParameter(0, user_phone);
		User user=(User) query.uniqueResult();
		return user;	
	}
	public boolean checkPwd(String user_phone,String user_pwd) {
		Session session=sessionFactory.getCurrentSession();
		User user=checkPhone(user_phone);
		boolean a=user.getUser_pwd().equals(user_pwd);
		if(a) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
