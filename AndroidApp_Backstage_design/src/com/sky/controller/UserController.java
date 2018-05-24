package com.sky.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sky.Bean.User;
import com.sky.common.Page;
import com.sky.common.indexPage;
import com.sky.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import net.sf.json.JSONObject;
import sun.print.resources.serviceui;
 



@Controller
public class UserController {
	@Autowired
	private UserService userService;
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/saveUser1")
	public void saveUser(HttpServletRequest request,HttpServletResponse response,String user_name,String user_pwd,String user_phone,@RequestParam MultipartFile user_image) throws IOException {
	//	System.out.print("连接"+user_name+user_pwd+user_phone);	
		User user=new User();
		user.setUser_name(user_name);//不可重复
		user.setUser_pwd(user_pwd);
		user.setUser_phone(user_phone);//不可重复
		user.setUser_image(user_image.getOriginalFilename());
		boolean bool= userService.saveUser(user,user_image,request);	
		if(bool) {
			
			response.getWriter().write("success");
			
		}else {
			response.getWriter().write("failure");
		}
		
	}

	//少一个单选框记住密码radi
	@RequestMapping("/getHeader")
	public void loginUser(HttpServletRequest request,HttpServletResponse response,String user_phone) throws IOException {
		System.out.println("登录"+user_phone);
		//用户名
		boolean b=userService.checkPhone(user_phone);
		User user=userService.selectByPhone(user_phone);
		String user_image=user.getUser_image();
		System.out.println(user_image);
	
		int img;
		if(b) {
			FileInputStream in=new FileInputStream(new File(user_image));
		    OutputStream out=response.getOutputStream();
		    while((img=in.read())!=-1) {
		    	out.write(img);	
		    	out.flush();
		    }
		}else {
			 response.getWriter().write("用户名不存在拟");	 
		}
		
	}
	@RequestMapping("loginUser")
	public void login(HttpServletRequest request,HttpServletResponse response,String user_phone,String user_pwd) throws IOException {
		//密码
		boolean c=userService.checkPwd(user_phone,user_pwd);
		if(c) {
		//	response.getWriter().write();
			User user=userService.selectByPhone(user_phone);
			JSONObject json = JSONObject.fromObject(user);
			System.out.print(json.toString());
			 response.getWriter().write(json.toString());	 
		}else {
			response.getWriter().write("failure");
		}
	}
	//树洞
	@RequestMapping("writeLetter")
	public void writeLetter(HttpServletRequest request,HttpServletResponse response,int user_id,String content)
	
}
