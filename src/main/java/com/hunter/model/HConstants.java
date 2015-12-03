package com.hunter.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 集群配置表，这种方法不好。
 * @author hun
 * @Description: TODO
 * @date 2015年11月16日 下午10:16:26
 */
@Deprecated
@Entity
@Table(name="hconstants")
public class HConstants implements Serializable, ObjectInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String custKey;
	private String custValue;
	private String description;
	
	
	public HConstants() {
		super();
	}


	public HConstants(String custKey, String custValue, String description) {
		super();
		this.custKey = custKey;
		this.custValue = custValue;
		this.description = description;
	}


	@Override
	public Object setObjectByMap(Map<String, Object> map) {
		HConstants hc = new HConstants();
		hc.setCustKey((String)map.get("custKey"));
		hc.setCustValue((String)map.get("custValue"));
		hc.setDescription((String)map.get("description"));
		hc.setId((Integer)map.get("id"));
		return hc;
	}


	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getCustKey() {
		return custKey;
	}


	public void setCustKey(String custKey) {
		this.custKey = custKey;
	}


	public String getCustValue() {
		return custValue;
	}


	public void setCustValue(String custValue) {
		this.custValue = custValue;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
}
