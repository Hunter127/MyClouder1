package com.hunter.model;

import java.util.Map;

/**
 * 
 * @author hun
 * @Description: TODO
 * @date 2015年11月16日 上午11:39:55
 */
public interface ObjectInterface {
	/**
	 * 不用每个表都建立一个方法，这里根据表名自动装配
	 * @param map
	 * @return
	 */
	public Object setObjectByMap(Map<String,Object> map);

}
