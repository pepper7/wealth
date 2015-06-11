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

	public T findByKey(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.findByKey(" + model + ")");
		return sqlSession.selectOne(model.getClass().getName() + ".findByKey", model);
	}

	public List<T> findList(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.findList(" + model + ")");
		return sqlSession.selectList(model.getClass().getName() + ".findList", model);
	}

	public List<T> findList(T model, Map<String, Object> params) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.findList(" + model + "," + params + ")");
		return sqlSession.selectList(model.getClass().getName() + ".findListByParam", params);
	}

	public long countList(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.countList(" + model + ")");
		return sqlSession.selectOne(model.getClass().getName() + ".countList", model);
	}

	public long countList(T model, Map<String, Object> params) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.countList(" + model + "," + params + ")");
		return sqlSession.selectOne(model.getClass().getName() + ".countListByParam", params);
	}

	public int insert(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.insert(" + model + ")");
		return sqlSession.insert(model.getClass().getName() + ".insert", model);
	}

	public int update(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.update(" + model + ")");
		return sqlSession.update(model.getClass().getName() + ".update", model);
	}

	public int delete(T model) throws DataAccessException {
		logger.debug(">>>" + this.getClass().getPackage() + ".CommonDAO.delete(" + model + ")");
		return sqlSession.delete(model.getClass().getName() + ".delete", model);
	}
}
