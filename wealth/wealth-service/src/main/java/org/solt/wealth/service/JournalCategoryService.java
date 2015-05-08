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

	public Enums getJournalCategory(Enums enums) {
		return enumDao.getEnumsById(enums);
	}

	public List<Enums> findJournalCategory(Enums enums) {
		enums.setEnumType("CONSUMPTION_TYPE");
		return enumDao.findEnumsByParam(enums);
	}

	public boolean addJournalCategory(Enums enums) throws ServiceException,SQLException {
		enums.setEnumType("CONSUMPTION_TYPE");
		return enumDao.insertEnums(enums) > 0 ? true : false;
	}

	public boolean updateJournalCategory(Enums enums) {
		return enumDao.updateEnums(enums) > 0 ? true : false;
	}

	public boolean deleteJournalCategory(Enums enums) throws ServiceException{
		return enumDao.deleteEnums(enums) > 0 ? true : false;
	}

	public List<Enums> findJournalCategoryForLayer(Enums enums) {
		enums.setEnumType("CONSUMPTION_TYPE");
		return enumDao.findEnumsByParamForLayer(enums);
	}

}
