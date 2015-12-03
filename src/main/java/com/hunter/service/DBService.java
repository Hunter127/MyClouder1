package com.hunter.service;

import java.util.List;
import java.util.Map;

import com.hunter.model.File;
import com.hunter.model.LoginUser;
import com.hunter.model.UserData;

public interface DBService {
	/**
	 * 获取用户文件
	 * @param username
	 * @return
	 */
	public List<Object> getUserFile(String username,Integer page,Integer rows);
	/**
	 * 保存文件
	 * @param f
	 * @return
	 */
	public boolean saveFile(File f,String name);
	
	/**
	 *  保存用户
	 * @param o
	 * @return
	 */
	public boolean saveUser(LoginUser o);
	/**
	 * 用户登录检查
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean getLoginUser(String username,String password);
	/**
	 * 测试表中是否有数据
	 * @param tableName
	 * @return
	 */
	public boolean getTableData(String tableName);
	/**
	 * 获取tableName的所有数据并返回
	 * @param tableName
	 * @return
	 */
	public List<Object> getTableAllData(String tableName);
	/**
	 * 分页获取tableName所有数据
	 * @param tableName
	 * @param rows
	 * @param page
	 * @return
	 */
	public Map<String,Object> getTableData(String tableName,int rows,int page);
	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public boolean saveTableData(List<Object> list);
	public boolean deleteById(String tableName,String id);
	public boolean deleteTable(String tableName);
	/**
	 * 更新获取插入表
	 * 不用每个表都建立一个方法，这里根据表名自动装配
	 * @param tableName
	 * @param json
	 * @return
	 */
	public boolean updateOrSave(String tableName,String json);
	/**
	 * 获取hadoop集群配置
	 * @param key
	 * @return
	 */
	@Deprecated
	public String getHConstValue(String key);
	/**
	 * 初始化登录表
	 * @return
	 */
	public boolean insertLoginUser();
	/**
	 * 初始化HContants
	 * @return
	 */
	public boolean insertConstants();
	/**insertHConstants
	 * 初始化UserData,此处使用批量插入，，后期更好的使用jdbc批量插入
	 * @return
	 */
	public boolean insertUserDate(); 
	/**
	 * 批量插入xmlpath数据
	 * @param xmlPath
	 * @return
	 */
	public Map<String,Object> insertUserData(String xmlPath);
	public boolean insertUserData_b();
	public List<UserData> getTableData(String tableName,List<Integer> ids);
	/**
	 * 获取分类数据比例
	 * @param k
	 * @return
	 */
	public List<String> getPercent(int k);
	/**
	 * 获取推荐信息
	 * 1.根据id查询UserGroup看是否有记录
	 * 2.如果有记录，则用户有推荐的朋友：跳转到4；
	 * 3.如果没有，则返回说当前没有分组
	 * 4.根据查询的groupType再次查询所有该分组的id
	 * 5.根据4中的id去UserData中查找数据并显示
	 * @param id
	 * @param rows
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getRecommendData(String id,int rows,int page)
	throws Exception;
	

}
