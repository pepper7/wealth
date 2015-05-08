package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;
import org.solt.wealth.persist.IAccountingBookDAO;
import org.solt.wealth.persist.IJournalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingBookService implements IAccountingBookService {

	@Autowired
	private IAccountingBookDAO dao;
	@Autowired
	private IJournalDAO journalDao;

	public AccountingBook getAccount(AccountingBook accountingBook) {
		return dao.getAccountingBookById(accountingBook);
	}

	public List<AccountingBook> findAccounts(AccountingBook accountingBook) {
		return dao.findAccountingBookByParam(accountingBook);
	}

	public boolean addAccount(AccountingBook accountingBook) {
		boolean flag = false;
		if (dao.insertAccountingBook(accountingBook) > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean updateAccount(AccountingBook accountingBook) {
		boolean flag = false;
		if (dao.updateAccountingBook(accountingBook) > 0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean deleteAccount(AccountingBook accountingBook) throws ServiceException {
		boolean flag = false;
		Journal journal = new Journal();
		journal.setAccBookId(accountingBook.getAccBookId());
		List<Journal> list = journalDao.findJournal(journal);
		if(list.size()==0){
			if (dao.deleteAccountingBook(accountingBook) > 0) {
				flag = true;
			}
		}else{
			throw new ServiceException("该账簿中还有日记账记录，不可删除！");
		}
		return flag;
	}

	public List<Journal> findJournal(Journal journal) {
		return journalDao.findJournal(journal);
	}

	public List<Journal> findJournal(Map<String, Object> params) {
		return journalDao.findJournalByParam(params);
	}

	public boolean addJournal(Journal journal) {
		boolean flag = false;
		if (journalDao.insertJournal(journal) > 0) {
			flag = true;
		}
		return flag;
	}

	public Journal getJournal(Journal journal) {
		return journalDao.getJournalById(journal);
	}

	public boolean saveJournal(Journal journal) {
		boolean flag = false;
		if (journalDao.updateJournal(journal) > 0) {
			flag = true;
		}
		return flag;
	}

	public boolean deleteJournal(Journal journal) {
		boolean flag = false;
		if (journalDao.deleteJournal(journal) > 0) {
			flag = true;
		}
		return flag;
	}

	public Double totalJournal(Map<String, Object> params) {
		return journalDao.getTotalJournal(params);
	}

	

}
