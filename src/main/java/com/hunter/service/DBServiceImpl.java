package com.hunter.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hunter.dao.BaseDao;
import com.hunter.model.File;
import com.hunter.model.HConstants;
import com.hunter.model.LoginUser;
import com.hunter.model.UserData;
import com.hunter.util.Utils;

@Service("dBService")
public class DBServiceImpl implements DBService {
	private Logger log = LoggerFactory.getLogger(DBServiceImpl.class);
	@Resource(name = "baseDao")
	private BaseDao<Object> baseDao;

	@Override
	public List<Object> getUserFile(String username,Integer page,Integer rows) {
		String hql="SELECT f FROM File f WHERE f.loginuser.username=? ORDER BY creationDate DESC";
		Object[] param=new Object[]{username};
		return  baseDao.find(hql, param, page, rows);
		
		
	}
	@Override
	public boolean saveFile(File f,String name) {
		try {
			String hql = "from LoginUser lu where lu.username='" + name + "'";
			List<Object> lus = baseDao.find(hql);
			if (lus.size() < 1) {
				log.info("没有此用户，username:{}", name);
				return false;
			}
			if (lus.size() > 1) {
				log.info("登录检查多个重名用户，请检查数据库，用户名为：｛｝", name);
				return false;
			}
			LoginUser lu = (LoginUser) lus.get(0);
			f.setLoginuser(lu);
			baseDao.save(f);
			log.info("增加一个文件");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean saveUser(LoginUser o) {
		try {// 根据表名获取实体类，并赋值
				// Object o =
				// Utils.getEntity(Utils.getEntityPackages(tableName), json);
			baseDao.save(o);

			log.info("增加一位用户");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean getLoginUser(String username, String password) {
		String hql = "from LoginUser lu where lu.username='" + username + "'";
		List<Object> lus = baseDao.find(hql);
		if (lus.size() < 1) {
			log.info("没有此用户，username:{}", username);
			return false;
		}
		if (lus.size() > 1) {
			log.info("登录检查多个重名用户，请检查数据库，用户名为：｛｝", username);
			return false;
		}
		LoginUser lu = (LoginUser) lus.get(0);
		if (lu.getPassword().equals(password)) {
			log.info("用户：'" + username + "'登录成功！");
			return true;
		}
		return false;
	}

	@Override
	public boolean getTableData(String tableName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Object> getTableAllData(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getTableData(String tableName, int rows, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveTableData(List<Object> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(String tableName, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteTable(String tableName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOrSave(String tableName, String json) {
		try {// 根据表名获取实体类，并赋值
			Object o = Utils
					.getEntity(Utils.getEntityPackages(tableName), json);
			baseDao.saveOrUpdate(o);
			log.info("保存表{}！", new Object[] { tableName });
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 从数据库配置表中得到hadoop配置，不再使用。
	 * 
	 * @param key
	 * @return
	 */
	@Deprecated
	@Override
	public String getHConstValue(String key) {
		HConstants hc = null;
		try {
			hc = (HConstants) baseDao.find(
					"from HConstants hc where hc.custKey'" + key + "'").get(0);
			if (hc == null) {
				log.info("hadoop配置基础表找不到配置的key", key);
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("获取云平台配置信息出错key" + key);
		}
		return hc.getCustValue();
	}

	@Override
	public boolean insertLoginUser() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 不再使用配置表，就没有插入的必要
	 * 
	 * @return
	 */
	@Deprecated
	@Override
	public boolean insertConstants() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertUserDate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> insertUserData(String xmlPath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUserData_b() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserData> getTableData(String tableName, List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getPercent(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getRecommendData(String id, int rows, int page)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
