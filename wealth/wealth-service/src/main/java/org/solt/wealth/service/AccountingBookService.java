package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;
import org.solt.wealth.persist.AccountingBookDAO;
import org.solt.wealth.persist.JournalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountingBookService implements IAccountingBookService {

	@Autowired
	private AccountingBookDAO dao;
	@Autowired
	private JournalDAO journalDao;

	public AccountingBook getAccount(AccountingBook accountingBook) throws ServiceException {
		try {
			return dao.findByKey(accountingBook);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<AccountingBook> findAccounts(AccountingBook accountingBook) throws ServiceException {
		try {
			return dao.findList(accountingBook);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addAccount(AccountingBook accountingBook) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.insert(accountingBook) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean updateAccount(AccountingBook accountingBook) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.update(accountingBook) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
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
			list = journalDao.findList(journal);
			if (list.size() == 0) {
				if (dao.delete(accountingBook) > 0) {
					flag = true;
				}
			} else {
				throw new ServiceException("该账簿中还有日记账记录，不可删除！");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public List<Journal> findJournal(Journal journal) throws ServiceException {
		try {
			return journalDao.findList(journal);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Journal> findJournal(Map<String, Object> params) throws ServiceException {
		try {
			return journalDao.findList(params);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addJournal(Journal journal) throws ServiceException {
		boolean flag = false;
		try {
			if (journalDao.insert(journal) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public Journal getJournal(Journal journal) throws ServiceException {
		try {
			return journalDao.findByKey(journal);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean saveJournal(Journal journal) throws ServiceException {
		boolean flag = false;
		try {
			if (journalDao.update(journal) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean deleteJournal(Journal journal) throws ServiceException {
		boolean flag = false;
		try {
			if (journalDao.delete(journal) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public Double totalJournal(Map<String, Object> params) throws ServiceException {
		try {
			return journalDao.getTotalJournal(params);
		} catch (DataAccessException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

}
