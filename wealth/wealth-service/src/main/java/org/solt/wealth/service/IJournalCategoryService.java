package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.common.Enums;
import org.solt.wealth.model.common.Page;

public interface IJournalCategoryService {
	public Enums getJournalCategory(Enums enums) throws ServiceException;

	public List<Enums> findJournalCategory(Enums enums) throws ServiceException;

	public boolean addJournalCategory(Enums enums) throws ServiceException;

	public boolean updateJournalCategory(Enums enums) throws ServiceException;

	public boolean deleteJournalCategory(Enums enums) throws ServiceException;

	public List<Enums> findJournalCategoryForLayer(Enums enums) throws ServiceException;

	public Page<Enums> findJournalCategoryPage(Map<String, Object> params, Page<Enums> page) throws ServiceException;
}
