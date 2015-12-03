package com.hunter.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
/**
 * 泛型，增删查改各种数据
 * @author hun
 * @Description: TODO
 * @date 2015年11月26日 下午8:08:29 
 * @param <T>
 */
@Repository("baseDao")
@SuppressWarnings("all")
public class BaseDaoImpl<T> implements BaseDao<T> {
	private Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 获取与当前绑定的session
	 * @return
	 */
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable save(T o) {

		return this.getSession().save(o);
	}

	@Override
	public void delete(T o) {
		this.getSession().delete(o);

	}

	@Override
	public void update(T o) {
		this.getSession().update(o);

	}

	@Override
	public void saveOrUpdate(T o) {
		this.getSession().saveOrUpdate(o);

	}

	@Override
	public List<T> find(String hql) {

		return this.getSession().createQuery(hql).list();
	}

	/**
	 * 当hql语句需要拼接参数时，使用
	 */
	@Override
	public List<T> find(String hql, Object[] param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			
			}
		}
		return q.list();
	}

	@Override
	public List<T> find(String hql, List<Object> param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.list();
	}

	/**
	 * page是当前第几页
	 */
	@Override
	public List<T> find(String hql, Object[] param, Integer page, Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, List<Object> param, Integer page,
			Integer rows) {
		if (page == null || page < 1) {
			page = 1;
		}
		if (rows == null || rows < 1) {
			rows = 10;
		}
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();

	}

	@Override
	public Long count(String hql) {

		return (Long) this.getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public BigInteger sqlCount(String hql) {

		return (BigInteger) this.getSession().createSQLQuery(hql)
				.uniqueResult();
	}

	@Override
	public Long count(String hql, List<Object> param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return (Long) q.uniqueResult();
	}

	@Override
	public Integer executeHql(String hql) {
		return this.getSession().createQuery(hql).executeUpdate();
	}

	@Override
	public Integer executeHql(String hql, Object[] param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer executeHql(String hql, List<Object> param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

	@Override
	public Integer saveBatch(List<T> lists) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(Class<T> c, Serializable id) {
		return (T) this.getSession().get(c, id);
	}

	@Override
	public T get(String hql, Object[] param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public T get(String hql, List<Object> param) {
		List<T> l = this.find(hql, param);
		if (l != null && l.size() > 0) {
			return l.get(0);
		} else {
			return null;
		}
	}

	@Override
	public Long count(String hql, Object[] param) {
		Query q = this.getSession().createQuery(hql);
		if (param != null && param.length > 0) {
			for (int i = 0; i < param.length; i++) {
				q.setParameter(i, param[i]);
			}
		}
		return (Long) q.uniqueResult();
	}

}
