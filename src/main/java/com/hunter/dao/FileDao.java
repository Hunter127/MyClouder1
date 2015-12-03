package com.hunter.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hunter.model.LoginUser;

@Repository("fileDao")
public class FileDao {
	private Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 获取与当前绑定的session
	 * @return
	 */
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public void save(LoginUser o) {

		getSession().save(o);
	} 

}
