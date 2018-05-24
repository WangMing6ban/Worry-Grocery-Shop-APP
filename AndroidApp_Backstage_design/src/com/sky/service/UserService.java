package com.sky.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sky.Bean.User;
import com.sky.Dao.UserDao;

import com.sky.common.indexPage;

import jdk.nashorn.internal.runtime.UserAccessorProperty;





@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Transactional
	public boolean saveUser(User user, MultipartFile user_image, HttpServletRequest request) throws IOException {
		// TODO Auto-generated method stub
		ServletContext servletContext = request.getServletContext();
		String realPath = servletContext.getRealPath("/");
		File dir = new File(realPath + "upload");
		File dir1=new File(dir,"user");//父路径，子路径
		File dir2 = new File(dir1, user.getUser_phone());
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!dir1.exists()) {
			dir1.mkdir();
		}
		if (!dir2.exists()) {
			dir2.mkdir();
		}
		if (user_image != null) {
			File file = new File(dir2, user.getUser_image());
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(user_image.getBytes());
			fos.flush();
			fos.close();
		}
		user.setUser_image(realPath+"upload/user/"+user.getUser_phone()+"/"+user_image.getOriginalFilename());
		user.setUser_desc("");
		user.setUser_state("normal");
		boolean bool = userDao.saveUser(user);
		return bool;
	}
	@Transactional
	public User selectByPhone(String user_phone) {
		User user= userDao.checkPhone(user_phone);
		if(null!=user) {
			return user;
		}else {
			return null;
		}
		
	}
	@Transactional
	public boolean checkPhone(String user_phone) {
		User user=userDao.checkPhone(user_phone);
		if(null!=user) {
			return true;
		}else {
			return false;
		}
		
	}
	@Transactional
	public boolean checkPwd(String user_phone,String user_pwd) {
		boolean c=userDao.checkPwd(user_phone,user_pwd);
		return c;
		
	}
	
	
	
	
	
	
	
}
