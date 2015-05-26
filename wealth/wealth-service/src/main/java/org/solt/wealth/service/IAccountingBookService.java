package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;

public interface IAccountingBookService {

	public AccountingBook getAccount(AccountingBook accountingBook) throws ServiceException;

	public List<AccountingBook> findAccounts(AccountingBook accountingBook) throws ServiceException;

	public boolean addAccount(AccountingBook accountingBook) throws ServiceException;

	public boolean updateAccount(AccountingBook accountingBook) throws ServiceException;

	public boolean deleteAccount(AccountingBook accountingBook) throws ServiceException;

	public List<Journal> findJournal(Journal journal) throws ServiceException;

	public List<Journal> findJournal(Map<String, Object> params) throws ServiceException;

	public Double totalJournal(Map<String, Object> params) throws ServiceException;

	public boolean addJournal(Journal journal) throws ServiceException;

	public Journal getJournal(Journal journal) throws ServiceException;

	public boolean saveJournal(Journal journal) throws ServiceException;

	public boolean deleteJournal(Journal journal) throws ServiceException;

}
