package com.hunter.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hunter.service.DBServiceImpl;

/**
 * Hadoop工具类
 * 
 * @author hun
 * @Description: TODO
 * @date 2015年11月17日 下午9:32:18
 */
public class HUtils {
	private static Logger log = LoggerFactory.getLogger(HUtils.class);
	public static final double VERYSMALL = 0.00000000000000000000000000001;

	public static final String SOURCEFILE = "/user/root/_source/source_users.xml";

	private static final String DOWNLOAD_EXTENSION = ".dat";

	private static Configuration conf = null;
	private static FileSystem fs = null;
	private static boolean flag = true;
	private static Properties prop = null;
	private static boolean isComplete = true;//是否上传完成
	public static int size = 0;  //已上传的大小

	/**
	 * 以静态块获取配置文件的值
	 */
	static {
		try {
			prop = new Properties();
			InputStream inStream = HUtils.class.getClassLoader()
					.getResourceAsStream("util.properties");
			prop.load(inStream);
			log.info("读入集群配置文件内容。。。");
		} catch (Throwable e) {
			throw new ExceptionInInitializerError();
		}
	}

	/**
	 * 获得配置，，如果每次从数据库中取出。存进取出都麻烦。直接使用配置文件，读取配置文件。随该随用多好，所以不用HConstants表。
	 * 
	 * @return
	 */
	@Deprecated
	public static Configuration getConfs() {

		if (conf == null) {
			conf = new Configuration();
			// get configuration from db or file
			conf.setBoolean("mapreduce.app-submission.cross-platform", "true"
					.equals(Utils.getKey(
							"mapreduce.app-submission.cross-platform", flag)));// 配置使用跨平台提交任务
			conf.set("fs.defaultFS", Utils.getKey("fs.defaultFS", flag));// 指定namenode
			conf.set("mapreduce.framework.name",
					Utils.getKey("mapreduce.framework.name", flag)); // 指定使用yarn框架
			conf.set("yarn.resourcemanager.address",
					Utils.getKey("yarn.resourcemanager.address", flag)); // 指定resourcemanager
			conf.set("yarn.resourcemanager.scheduler.address", Utils.getKey(
					"yarn.resourcemanager.scheduler.address", flag));// 指定资源分配器
			conf.set("mapreduce.jobhistory.address",
					Utils.getKey("mapreduce.jobhistory.address", flag));
		}

		return conf;
	}

	/**
	 * 获取配置文件， 0.2版本
	 * 
	 * @return
	 */
	public static Configuration getConf() {
		log.info("开始配置集群...");
		if (conf == null) {
			conf = new Configuration();
			// get configuration from db or file
			conf.setBoolean(
					"mapreduce.app-submission.cross-platform",
					"true".equals(prop
							.getProperty("mapreduce.app-submission.cross-platform")));// 配置使用跨平台提交任务
			conf.set("fs.defaultFS", prop.getProperty("fs.defaultFS"));// 指定namenode
			conf.set("mapreduce.framework.name",
					prop.getProperty("mapreduce.framework.name")); // 指定使用yarn框架
			conf.set("yarn.resourcemanager.address",
					prop.getProperty("yarn.resourcemanager.address")); // 指定resourcemanager
			conf.set("yarn.resourcemanager.scheduler.address",
					prop.getProperty("yarn.resourcemanager.scheduler.address"));// 指定资源分配器
			conf.set("mapreduce.jobhistory.address",
					prop.getProperty("mapreduce.jobhistory.address"));
			conf.setBoolean("dfs.support.append", true);
		}
		log.info("配置完成");
		return conf;
	}

	public static FileSystem getFs() {
		if (fs == null) {
			try {
				log.info("HDFS文件系统 连接中...");
				fs = FileSystem.get(getConf());
				log.info("HDFS文件系统 连接完成");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fs;
	}

	/**
	 * 返回HDFS路径,该函数在0.1版本中是增加hdfs:的作用
	 * 
	 * @param url
	 * @return
	 */
	@Deprecated
	public static String getHDFSPath(String url) {
		return Utils.getKey("fs.defaultFS", flag) + url;
	}

	/**
	 * 获取Path路径 如果url包含hdfs:// ，那么flag使用true，否则flag使用false
	 * 
	 * @param url
	 * @param flag
	 * @return
	 */
	public static Path getHDFSPath(String url, String flag) {
		if ("true".equals(flag)) {
			return new Path(url);
		} else {
			return new Path(getHDFSPath(url));
		}

	}

	/**
	 * 上传文件
	 * 
	 * @param localPath
	 * @param hdfsPath
	 * @return
	 */
	public static Map<String, Object> upload(String localPath, String hdfsPath) {
		Map<String, Object> ret = new HashMap<String, Object>();
		FileSystem fs = getFs();
		Path src = new Path(localPath);
		Path dst = new Path(hdfsPath);
		try {
			fs.copyFromLocalFile(src, dst);
			isComplete = false;
			Utils.simpleLog(localPath + "上传至" + hdfsPath + "成功");
		} catch (Exception e) {
			ret.put("flag", "false");
			ret.put("msg", e.getMessage());
			e.printStackTrace();
			return ret;
		}
		ret.put("flag", "true");
		return ret;

	}
	/**
	 * 实时获取指定目录的大小
	 * @return
	 */
	public static int getSize(String path){
		FileSystem fs = getFs();
		Path src = new Path(path);
		try {
			size=fs.open(src).available();
			return size;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 是否上传完成
	 * @return
	 */
	public static boolean getStatus(){
		return isComplete;
	}
	

	/**
	 * 创建文件夹
	 * @param path
	 */
	public static void mkdir(String path) {
		FileSystem fs = getFs();
		Path src = new Path(path);
		boolean isok;
		try {
			isok = fs.mkdirs(src);
			if (isok) {
				System.out.println("create dir ok!");
			} else {
				System.out.println("create dir failure");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}

	}

	/**
	 * 下载文件
	 * 
	 * @param hdfsPath
	 * @param localPath
	 * @return
	 */
	public static Map<String, Object> downLoad(String hdfsPath, String localPath) {
		Map<String, Object> ret = new HashMap<String, Object>();
		FileSystem fs = getFs();
		Path dst = new Path(localPath);
		Path src = new Path(hdfsPath);
		try {
			RemoteIterator<LocatedFileStatus> fss = fs.listFiles(src, true);
			int i = 0;
			while (fss.hasNext()) {
				LocatedFileStatus file = fss.next();
				if (file.isFile() ) {
					fs.copyToLocalFile(false, file.getPath(), new Path(dst,
							"hdfs_" + (i++) + HUtils.DOWNLOAD_EXTENSION), true);
					log.info("下载中....");
				}
				
			}
			log.info("下载完成");
		} catch (Exception e) {
			ret.put("flag", "false");
			ret.put("msg", e.getMessage());
			e.printStackTrace();
			return ret;
		}
		ret.put("flag", "true");
		return ret;

	}

}
