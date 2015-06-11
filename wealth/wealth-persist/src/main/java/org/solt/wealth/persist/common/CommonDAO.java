package org.solt.wealth.persist.common;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * DAO泛型抽象类
 * @author yasin
 * @version 1.0
 * @createdDate 2015-06-11
 */
@Repository
public abstract class CommonDAO<T> {

	private final Logger logger = Logger.getLogger(CommonDAO.class);

	@Autowired
	protected SqlSessionTemplate sqlSession;

	private final Class<T> clazz;

	public CommonDAO(Class<T> clazz) {
		this.clazz = clazz;
		logger.debug(clazz);
	}

	public T findByKey(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.findByKey(" + model + ")");
		return sqlSession.selectOne(clazz.getName() + ".findByKey", model);
	}

	public List<T> findList(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.findList(" + model + ")");
		return sqlSession.selectList(clazz.getName() + ".findList", model);
	}

	public List<T> findList(Map<String, Object> params) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.findList(" + params + ")");
		return sqlSession.selectList(clazz.getName() + ".findListByParam", params);
	}

	public long countList(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.countList(" + model + ")");
		return sqlSession.selectOne(clazz.getName() + ".countList", model);
	}

	public long countList(Map<String, Object> params) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.countList(" + params + ")");
		return sqlSession.selectOne(clazz.getName() + ".countListByParam", params);
	}

	public int insert(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.insert(" + model + ")");
		return sqlSession.insert(clazz.getName() + ".insert", model);
	}

	public int update(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.update(" + model + ")");
		return sqlSession.update(clazz.getName() + ".update", model);
	}

	public int delete(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.delete(" + model + ")");
		return sqlSession.delete(clazz.getName() + ".delete", model);
	}
}
