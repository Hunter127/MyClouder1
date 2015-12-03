package com.hunter.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "file")
public class File implements ObjectInterface, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String filename;
	private Date creationDate;
	private long size;
	private String url;
	private LoginUser loginuser;

	public File() {
	}

	public File(Integer id, String filename, Date creationDate, Integer size,
			LoginUser loginuser) {
		super();
		this.id = id;
		this.filename = filename;
		this.creationDate = creationDate;
		this.size = size;
		this.loginuser = loginuser;
	}

	@Override
	public Object setObjectByMap(Map<String, Object> map) {
		File file = new File();
		file.setCreationDate((Date) map.get("creationDate"));
		file.setFilename((String) map.get("filename"));
		file.setId((Integer) map.get("id"));
		file.setLoginuser((LoginUser) map.get("loginuser"));
		file.setSize((Integer) map.get("size"));

		return file;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * fetch为加载策略,,fetch=FetchType.LAZY这个为懒加载
	 * @return
	 */
	@ManyToOne(targetEntity=LoginUser.class,cascade={CascadeType.ALL}/*,fetch=FetchType.LAZY*/)
	public LoginUser getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(LoginUser loginuser) {
		this.loginuser = loginuser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
