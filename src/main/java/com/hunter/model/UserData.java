package com.hunter.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户数据表 主要存储stackoverflow上面用户的数据
 * 
 * @author hun
 * @Description: TODO
 * @date 2015年11月16日 下午7:39:13
 */
@Entity
@Table(name = "userdata")
public class UserData implements Serializable, ObjectInterface {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer reputation;
	private Date creationDate;
	private String displayName;
	private String emailHash;
	private Date lastAccessDate;
	private String websiteUrl;
	private String location;
	private Integer age;
	private String aboutMe;
	private Integer views;
	private Integer upVotes;
	private Integer downVotes;

	public UserData() {
		super();
	}

	public UserData(String[] s) throws ParseException {
		if (s[1] != null) {
			this.reputation = Integer.parseInt(s[1]);
		}

		this.creationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
				.parse(s[2].replaceAll("T", " "));
		this.displayName = s[3];
		this.emailHash = s[4];
		this.lastAccessDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss")
				.parse(s[5].replaceAll("T", " "));
		this.location = s[6];
		if (s[7] != null) {
			this.age = Integer.parseInt(s[7]);
		}
		this.aboutMe = s[8];
		if (s[9] != null) {
			this.views = Integer.parseInt(s[9]);
		}
		if (s[10] != null) {
			this.upVotes = Integer.parseInt(s[10]);
		}
		if (s[11] != null) {
			this.downVotes = Integer.parseInt(s[11]);
		}

	}

	@Override
	public Object setObjectByMap(Map<String, Object> map) {
		UserData ud = new UserData();
		ud.setAboutMe((String) map.get("aboutMe"));
		ud.setAge((Integer) map.get("age"));
		ud.setCreationDate((Date) map.get("creationDate"));// 类型格式？
		ud.setDisplayName((String) map.get("displayName"));
		ud.setDownVotes((Integer) map.get("downVotes"));
		ud.setEmailHash((String) map.get("emailHash"));
		ud.setId((Integer) map.get("id"));
		ud.setLastAccessDate((Date) map.get("lastAccessDate"));
		ud.setLocation((String) map.get("location"));
		ud.setReputation((Integer) map.get("reputation"));
		ud.setUpVotes((Integer) map.get("upVotes"));
		ud.setViews((Integer) map.get("views"));
		ud.setWebsiteUrl((String) map.get("webSiteUrl"));
		return ud;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getReputation() {
		return reputation;
	}

	public void setReputation(Integer reputation) {
		this.reputation = reputation;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmailHash() {
		return emailHash;
	}

	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}

	public Date getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(Date lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(length = 1000)
	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(Integer upVotes) {
		this.upVotes = upVotes;
	}

	public Integer getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(Integer downVotes) {
		this.downVotes = downVotes;
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}

	
}
