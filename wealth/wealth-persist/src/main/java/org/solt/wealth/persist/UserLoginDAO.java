package org.solt.wealth.persist;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.solt.wealth.model.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * UserLogin DAO Class
 * @author solt
 */
@Repository
public class UserLoginDAO implements IUserLoginDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public UserLogin getUserLogin(UserLogin model) throws DataAccessException {
		return sqlSession.selectOne("org.solt.wealth.model.UserLogin.getUserLogin", model);
	}

	public List<UserLogin> findUserLoginByParam(UserLogin model) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.UserLogin.findUserLoginByParam", model);
	}

	public long countUserLoginByParam(UserLogin model) throws DataAccessException {
		return sqlSession.selectOne("org.solt.wealth.model.UserLogin.countPartyByParam", model);
	}

	public int insertUserLogin(UserLogin model) throws DataAccessException {
		return sqlSession.insert("org.solt.wealth.model.UserLogin.insertUserLogin", model);
	}

	public int updateUserLogin(UserLogin model) throws DataAccessException {
		return sqlSession.update("org.solt.wealth.model.UserLogin.updateUserLogin", model);
	}

	public int deleteUserLogin(UserLogin model) throws DataAccessException {
		return sqlSession.delete("org.solt.wealth.model.UserLogin.deleteUserLogin", model);
	}

}
