package org.solt.wealth.service;

import java.util.List;

import org.solt.wealth.model.UserLogin;

public interface IUserLoginService {

	public UserLogin getUserLogin(UserLogin userLogin) throws ServiceException;

	public List<UserLogin> findUserLogins(UserLogin userLogin) throws ServiceException;

	public boolean addUserLogin(UserLogin userLogin) throws ServiceException;

	public boolean updateUserLogin(UserLogin userLogin) throws ServiceException;

	public boolean deleteUserLogin(UserLogin userLogin) throws ServiceException;

	public UserLogin login(UserLogin userLogin) throws ServiceException;
}
