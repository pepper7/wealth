package org.solt.wealth.persist;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.solt.wealth.model.AccountingBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountingBookDAO implements IAccountingBookDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public AccountingBook getAccountingBookById(AccountingBook accountingBook) {
		return (AccountingBook)sqlSession.selectOne("org.solt.wealth.model.AccountingBook.getAccountingBook", accountingBook);
	}

	public List<AccountingBook> findAccountingBookByParam(AccountingBook accountingBook) {
		return sqlSession.selectList("org.solt.wealth.model.AccountingBook.findAccountingBookByParam", accountingBook);
	}

	public int insertAccountingBook(AccountingBook accountingBook) {
		return sqlSession.insert("org.solt.wealth.model.AccountingBook.insertAccountingBook", accountingBook);
	}

	public int updateAccountingBook(AccountingBook accountingBook) {
		return sqlSession.update("org.solt.wealth.model.AccountingBook.updateAccountingBook", accountingBook);
	}

	public int deleteAccountingBook(AccountingBook accountingBook) {
		return sqlSession.delete("org.solt.wealth.model.AccountingBook.deleteAccountingBook", accountingBook);
	}

}
