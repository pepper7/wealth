package org.solt.wealth.service;

import java.sql.SQLException;
import java.util.List;

import org.solt.wealth.model.common.Enums;

public interface IJournalCategoryService {
	public Enums getJournalCategory(Enums enums);

	public List<Enums> findJournalCategory(Enums enums);

	public boolean addJournalCategory(Enums enums) throws ServiceException, SQLException;

	public boolean updateJournalCategory(Enums enums);

	public boolean deleteJournalCategory(Enums enums) throws ServiceException;

	public List<Enums> findJournalCategoryForLayer(Enums enums);
}
