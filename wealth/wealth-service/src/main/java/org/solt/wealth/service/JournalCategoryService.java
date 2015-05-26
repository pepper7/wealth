package org.solt.wealth.service;

import java.sql.SQLException;
import java.util.List;

import org.solt.wealth.model.common.Enums;
import org.solt.wealth.persist.common.IEnumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalCategoryService implements IJournalCategoryService {
	@Autowired
	private IEnumDAO enumDao;

	public Enums getJournalCategory(Enums enums) throws ServiceException {
		try {
			return enumDao.getEnumsById(enums);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Enums> findJournalCategory(Enums enums) throws ServiceException {
		enums.setEnumType("CONSUMPTION_TYPE");
		try {
			return enumDao.findEnumsByParam(enums);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addJournalCategory(Enums enums) throws ServiceException {
		enums.setEnumType("CONSUMPTION_TYPE");
		try {
			return enumDao.insertEnums(enums) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean updateJournalCategory(Enums enums) throws ServiceException {
		try {
			return enumDao.updateEnums(enums) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean deleteJournalCategory(Enums enums) throws ServiceException {
		try {
			return enumDao.deleteEnums(enums) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Enums> findJournalCategoryForLayer(Enums enums) throws ServiceException {
		enums.setEnumType("CONSUMPTION_TYPE");
		try {
			return enumDao.findEnumsByParamForLayer(enums);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

}
