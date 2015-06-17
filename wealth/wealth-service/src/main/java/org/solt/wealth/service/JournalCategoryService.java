package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.common.Enums;
import org.solt.wealth.model.common.Page;
import org.solt.wealth.persist.common.EnumDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JournalCategoryService implements IJournalCategoryService {
	@Autowired
	private EnumDAO enumDao;

	public Enums getJournalCategory(Enums enums) throws ServiceException {
		try {
			return enumDao.findByKey(enums);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Enums> findJournalCategory(Enums enums) throws ServiceException {
		enums.setEnumType("CONSUMPTION_TYPE");
		try {
			return enumDao.findList(enums);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public boolean addJournalCategory(Enums enums) throws ServiceException {
		enums.setEnumType("CONSUMPTION_TYPE");
		try {
			return enumDao.insert(enums) > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public boolean updateJournalCategory(Enums enums) throws ServiceException {
		try {
			return enumDao.update(enums) > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean deleteJournalCategory(Enums enums) throws ServiceException {
		try {
			return enumDao.delete(enums) > 0 ? true : false;
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Enums> findJournalCategoryForLayer(Enums enums) throws ServiceException {
		enums.setEnumType("CONSUMPTION_TYPE");
		try {
			return enumDao.findEnumsByParamForLayer(enums);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public Page<Enums> findJournalCategoryPage(Map<String, Object> params, Page<Enums> page) throws ServiceException {
		try {
			params.put("enumType", "CONSUMPTION_TYPE");
			return enumDao.findList(params, page);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

}
