package org.solt.wealth.persist;

import java.util.List;

import org.solt.wealth.model.AccountingBook;

public interface IAccountingBookDAO {

	AccountingBook getAccountingBookById(AccountingBook accountingBook);

	List<AccountingBook> findAccountingBookByParam(AccountingBook accountingBook);

	int insertAccountingBook(AccountingBook accountingBook);

	int updateAccountingBook(AccountingBook accountingBook);

	int deleteAccountingBook(AccountingBook accountingBook);
}
