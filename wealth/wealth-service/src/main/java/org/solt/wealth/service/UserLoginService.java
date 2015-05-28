package org.solt.wealth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solt.wealth.model.UserLogin;
import org.solt.wealth.persist.IUserLoginDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements IUserLoginService {
	private static final Logger logger = LoggerFactory.getLogger(UserLoginService.class);

	@Autowired
	private IUserLoginDAO dao;

	public UserLogin getUserLogin(UserLogin userLogin) throws ServiceException {
		try {
			return dao.getUserLogin(userLogin);
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<UserLogin> findUserLogins(UserLogin userLogin) throws ServiceException {
		try {
			return dao.findUserLoginByParam(userLogin);
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addUserLogin(UserLogin userLogin) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.insertUserLogin(userLogin) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean updateUserLogin(UserLogin userLogin) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.updateUserLogin(userLogin) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean deleteUserLogin(UserLogin userLogin) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.deleteUserLogin(userLogin) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

}
