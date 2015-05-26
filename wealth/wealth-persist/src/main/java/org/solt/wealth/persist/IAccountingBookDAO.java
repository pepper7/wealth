package org.solt.wealth.persist;

import java.sql.SQLException;
import java.util.List;

import org.solt.wealth.model.AccountingBook;

public interface IAccountingBookDAO {

	AccountingBook getAccountingBookById(AccountingBook accountingBook) throws SQLException;

	List<AccountingBook> findAccountingBookByParam(AccountingBook accountingBook) throws SQLException;

	int insertAccountingBook(AccountingBook accountingBook) throws SQLException;

	int updateAccountingBook(AccountingBook accountingBook) throws SQLException;

	int deleteAccountingBook(AccountingBook accountingBook) throws SQLException;
}
