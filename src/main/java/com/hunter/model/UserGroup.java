package com.hunter.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 用户群组表
 * @author hun
 * @Description: TODO
 * @date 2015年11月16日 下午9:00:37
 */
@Entity
@Table(name="usergroup")
public class UserGroup implements Serializable,ObjectInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer userId;
	private Integer groupType;
	@Override
	public Object setObjectByMap(Map<String, Object> map) {
		UserGroup lu = new UserGroup();
		lu.setGroupType((Integer)map.get("group"));
		lu.setId((Integer)map.get("id"));
		lu.setUserId((Integer)map.get("userId"));
		return lu;
	}
	public UserGroup(Integer userId, Integer groupType) {
		super();
		this.userId = userId;
		this.groupType = groupType;
	}
	public UserGroup() {
		super();
	}
	public UserGroup(Integer id, Integer userId, Integer groupType) {
		super();
		this.id = id;
		this.userId = userId;
		this.groupType = groupType;
	}
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getGroupType() {
		return groupType;
	}
	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	
	
	
}
