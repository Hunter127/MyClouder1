package com.hunter.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.struts2.ServletActionContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.ContextLoader;

import com.hunter.model.ObjectInterface;
import com.hunter.service.DBService;

/**
 * 工具类
 * 
 * @author hun
 * @Description: TODO
 * @date 2015年11月17日 下午9:45:51
 */
public class Utils {

	public static String baseServicePackges = "com.hunter.service.*";
	// 国际化资源
	private static ResourceBundle resb = null;
	private static PrintWriter writer = null;
	// 画决策树时每个文件提取的记录数
	public static final int GETDRAWPICRECORDS_EVERYFILE = 500;
	private static String[] userdata_attributes = new String[] { "Id",
			"Reputation", "CreationDate", "DisplayName", "EmailHash",
			"LastAccessDate", "Location", "Age", "AboutMe", "Views", "UpVotes",
			"DownVotes" };
	public static String[] useful_attributes = new String[] { "reputations",
			"upVotes", "downVotes", "views" };
	private static String userdata_elementName = "row";
	private static String userdata_xmlPath = "ask_ubuntu_users.xml";

	private static int counter = 0; // 在任务运行时返回递增的点字符串

	/**
	 * 这种从数据库获取hadoop配置方法不再使用，，改为从配置文件直接读取。
	 * @param key
	 * @param dbOrFile
	 * @return
	 */
	@Deprecated
	public static String getKey(String key, boolean dbOrFile) {
		if (dbOrFile) {
			DBService dbService = (DBService) SpringUtil.getBean("dBService");
			return dbService.getHConstValue(key);
		}
		if (resb == null) {
			Locale locale = new Locale("zh", "CN");
			resb = ResourceBundle.getBundle("util", locale);

		}
		return resb.getString(key);
	}

	/**
	 * 向PrintWriter中输入数据,输入的数据做为ajax的响应数据
	 * 
	 * @param info
	 */
	public static void write2PrintWriter(String info) {
		try {
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			writer = ServletActionContext.getResponse().getWriter();

			writer.write(info);// 响应输入
			System.out.println(info);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void write2PrintWriter(boolean flag) {
		write2PrintWriter(String.valueOf(flag));

	}

	/**
	 * 通过反射用类名获取实体类
	 * @param tableName
	 * @param json
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Object getEntity(String tableName, String json)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, JsonParseException, JsonMappingException,
			IOException {
		Class<?> cl = Class.forName(tableName);
		ObjectInterface o = (ObjectInterface) cl.newInstance();
		Map<String, Object> map = new HashMap<String, Object>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(json, Map.class);
			return o.setObjectByMap(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getEntityPackages(String table){
		return "com.hunter.model."+table;
	}
	/**
	 * 获得根目录
	 * 
	 * @return
	 */
	private static String getRootPath() {
		return ContextLoader.getCurrentWebApplicationContext()
				.getServletContext().getRealPath("/");
	}

	/**
	 * 获取根目录路径下面的subPath路径
	 * 
	 * @param subPath
	 * @return
	 */
	public static String getRootPathBasedPath(String subPath) {
		return getRootPath() + subPath;
	}

	/**
	 * 简单日志
	 */
	public static void simpleLog(String msg){
		System.out.println(new java.util.Date()+":"+msg);
	}
}
