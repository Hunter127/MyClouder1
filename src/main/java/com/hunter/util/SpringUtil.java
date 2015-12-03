package com.hunter.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring bean 获取类
 * @author hun
 * @Description: TODO
 * @date 2015年11月18日 上午11:30:42
 */
public class SpringUtil implements ApplicationContextAware {
	
	private static ApplicationContext ac = null;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.ac = arg0;
	}
	public synchronized static Object getBean(String name){
		return ac.getBean(name);
	}

}
