package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;
import org.solt.wealth.model.common.Enums;

public interface IAccountingBookService {

	public AccountingBook getAccount(AccountingBook accountingBook);

	public List<AccountingBook> findAccounts(AccountingBook accountingBook);

	public boolean addAccount(AccountingBook accountingBook);

	public boolean updateAccount(AccountingBook accountingBook);

	public List<Journal> findJournal(Journal journal);

	public List<Journal> findJournal(Map<String, Object> params);

	public Double totalJournal(Map<String, Object> params);

	public boolean addJournal(Journal journal);

	public Journal getJournal(Journal journal);

	public boolean saveJournal(Journal journal);

	public boolean deleteJournal(Journal journal);

	public List<Enums> getCategory();

	public List<Enums> getCategory(Enums enums);
}
