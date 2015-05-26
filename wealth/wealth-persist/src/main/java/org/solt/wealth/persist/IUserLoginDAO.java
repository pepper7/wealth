package org.solt.wealth.persist;

import java.util.List;

import org.springframework.dao.DataAccessException;

import org.solt.wealth.model.UserLogin;

/**
 * UserLogin DAO Interface
 * @author salt
 */
public interface IUserLoginDAO {

	UserLogin getUserLogin(UserLogin model) throws DataAccessException;

	List<UserLogin> findUserLoginByParam(UserLogin model) throws DataAccessException;

	long countUserLoginByParam(UserLogin model) throws DataAccessException;

	int insertUserLogin(UserLogin model) throws DataAccessException;

	int updateUserLogin(UserLogin model) throws DataAccessException;

	int deleteUserLogin(UserLogin model) throws DataAccessException;
}
