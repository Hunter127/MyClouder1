package com.hunter.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="loginuser")
public class LoginUser implements Serializable,ObjectInterface{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String username;
	private String password;
	private String description;
	private Set<File> files = new HashSet<File>();

	public LoginUser(){}
	public LoginUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	@Override
	public Object setObjectByMap(Map<String, Object> map) {
		LoginUser lu = new LoginUser();
		lu.setDescription((String)map.get("description"));
		lu.setId((Integer)map.get("id"));
		lu.setPassword((String)map.get("password"));
		lu.setUsername((String)map.get("username"));
		lu.setFiles((Set<File>)map.get("file"));
		return lu;
	}
	@Id
	@Column(name="id") 
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(targetEntity=File.class,mappedBy="loginuser",cascade=javax.persistence.CascadeType.ALL)
	public Set<File> getFiles() {
		return files;
	}
	
	public void setFiles(Set<File> files) {
		this.files = files;
	}
	
	

}
