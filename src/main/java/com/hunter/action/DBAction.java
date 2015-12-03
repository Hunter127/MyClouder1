package com.hunter.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.Path;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hunter.service.DBService;
import com.hunter.util.HUtils;
import com.hunter.util.Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller("dBAction")
@Scope("prototype")
public class DBAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Logger log = LoggerFactory.getLogger(DBAction.class);
	Date creationDate = new Date(new java.util.Date().getTime());
	@Resource
	private DBService dBService;
	private String rows;// 页大小
	private String page;// 当前第几页

	private String tableName;
	private File file = null;
	private String filename;

	private String id;
	private String json;

	private InputStream fileInput;
	private String url;// 下载的url

	/**
	 * 下载文件
	 * 
	 * @throws IOException
	 */
	public String DownFile() throws IOException {
		log.info(filename);
		log.info("开始下载");
		this.fileInput = getFileInput();
		return "success";
	}

	/**
	 * 上传文件,,360竟然获取不到session
	 */
	public void UpFile() {
		log.info("开始上传");
		String name = (String) getSession().get("uname");
		com.hunter.model.File f = new com.hunter.model.File();
		f.setCreationDate(creationDate);
		f.setFilename(filename);
		f.setSize(file.length() / 1024 / 1024);
		f.setUrl("/" + name + "/" + filename);
		dBService.saveFile(f, name);
		HUtils.upload(file.getAbsolutePath(), "/" + name + "/" + filename);
		log.info("上传完成");

	}

	/**
	 * 获取用户文件文件目录
	 */
	public void getUserFile() {
		// 每页显示条数
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		String name = (String) getSession().get("uname");
		List<Object> list = dBService.getUserFile(name, intPage, number);
		JSONArray json = new JSONArray();
		for (Object object : list) {
			JSONObject jo = new JSONObject();
			com.hunter.model.File f = (com.hunter.model.File) object;
			jo.put("url", f.getUrl());
			jo.put("filename", f.getFilename());
			jo.put("size", f.getSize());
			jo.put("creationDate", f.getCreationDate());
			json.add(jo);
		}
		Utils.write2PrintWriter(json.toJSONString());
	}

	/**
	 * 根据tablename分页获取表数据
	 */
	public void getTableData() {
		// 每页显示条数
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		Map<String, Object> list = dBService.getTableData(tableName, intPage,
				number);
		String json = JSON.toJSONString(list);
		log.info(json);
		Utils.write2PrintWriter(json);
	}

	/**
	 * 获取推荐信息
	 */
	public void getRecommentData() {

	}

	/**
	 * 按照id删除表中数据
	 */
	public void deleteById() {
		boolean del = dBService.deleteById(tableName, id);

	}

	/**
	 * 更新或保存数据
	 */
	public void updateSave() {
		boolean delSuccess = dBService.updateOrSave(tableName, json);
		String msg = "fail";
		if (delSuccess) {
			msg = "success";
		}
		log.info("保存表" + tableName + (delSuccess ? "成功" : "失败" + "!"));
		Utils.write2PrintWriter(msg);

	}

	/**
	 * 初始化表
	 */
	public void initialTable() {
		boolean initRet = false;
		if ("LoginUser".equals(tableName)) {
			initRet = dBService.insertLoginUser();
		} else if ("HConstants".equals(tableName)) {
			initRet = dBService.insertConstants();
		} else {
			initRet = dBService.insertUserDate();
		}

		Utils.write2PrintWriter(initRet);
	}

	public DBService getdBService() {
		return dBService;
	}

	public void setdBService(DBService dBService) {
		this.dBService = dBService;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	private Map<String, Object> getSession() {

		return ActionContext.getContext().getSession();
	}

	public InputStream getFileInput() throws IOException {
		Path srcPath = new Path(url);
		InputStream in = null;
		in = HUtils.getFs().open(srcPath);
		return in;
	}

	public void setFileInput(InputStream fileInput) {
		this.fileInput = fileInput;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
