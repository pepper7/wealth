package org.solt.wealth.service;

import java.sql.SQLException;
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

	public AccountingBook getAccount(AccountingBook accountingBook) throws ServiceException{
		try {
			return dao.getAccountingBookById(accountingBook);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<AccountingBook> findAccounts(AccountingBook accountingBook) throws ServiceException{
		try {
			return dao.findAccountingBookByParam(accountingBook);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addAccount(AccountingBook accountingBook)throws ServiceException {
		boolean flag = false;
		try {
			if (dao.insertAccountingBook(accountingBook) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean updateAccount(AccountingBook accountingBook)throws ServiceException {
		boolean flag = false;
		try {
			if (dao.updateAccountingBook(accountingBook) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}
	
	public boolean deleteAccount(AccountingBook accountingBook) throws ServiceException {
		boolean flag = false;
		Journal journal = new Journal();
		journal.setAccBookId(accountingBook.getAccBookId());
		List<Journal> list;
		try {
			list = journalDao.findJournal(journal);
			if(list.size()==0){
				if (dao.deleteAccountingBook(accountingBook) > 0) {
					flag = true;
				}
			}else{
				throw new ServiceException("该账簿中还有日记账记录，不可删除！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public List<Journal> findJournal(Journal journal) throws ServiceException{
		try {
			return journalDao.findJournal(journal);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Journal> findJournal(Map<String, Object> params) throws ServiceException{
		try {
			return journalDao.findJournalByParam(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addJournal(Journal journal) throws ServiceException{
		boolean flag = false;
		try {
			if (journalDao.insertJournal(journal) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public Journal getJournal(Journal journal) throws ServiceException{
		try {
			return journalDao.getJournalById(journal);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean saveJournal(Journal journal) throws ServiceException{
		boolean flag = false;
		try {
			if (journalDao.updateJournal(journal) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean deleteJournal(Journal journal) throws ServiceException{
		boolean flag = false;
		try {
			if (journalDao.deleteJournal(journal) > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public Double totalJournal(Map<String, Object> params)throws ServiceException {
		try {
			return journalDao.getTotalJournal(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	

}
