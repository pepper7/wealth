package org.solt.wealth.service;

import java.util.List;

import org.solt.wealth.model.common.Enums;

public interface IJournalCategoryService {
	public Enums getJournalCategory(Enums enums) throws ServiceException;

	public List<Enums> findJournalCategory(Enums enums) throws ServiceException;

	public boolean addJournalCategory(Enums enums) throws ServiceException;

	public boolean updateJournalCategory(Enums enums) throws ServiceException;

	public boolean deleteJournalCategory(Enums enums) throws ServiceException;

	public List<Enums> findJournalCategoryForLayer(Enums enums) throws ServiceException;
}
