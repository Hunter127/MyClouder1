package com.hunter.action;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hunter.service.DBService;
import com.hunter.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("loginUserAction")
@Scope("prototype")
public class LoginUserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(LoginUserAction.class);
	@Resource
	private DBService dBService;
	
	private String username;
	private String password;
	
	public void login(){
		log.info("User:{}正在登录系统...",username);
		try{
			boolean flag = dBService.getLoginUser(username, password);
			getSession().put("userId", flag);
			getSession().put("uname", username);
		
			System.out.println(getSession().get("uname"));
			Utils.write2PrintWriter(flag);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public DBService getdBService() {
		return dBService;
	}

	public void setdBService(DBService dBService) {
		this.dBService = dBService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Map<String,Object> getSession(){
		return ActionContext.getContext().getSession();
	}
	
}
