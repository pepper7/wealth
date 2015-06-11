package org.solt.wealth.persist;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.persist.common.CommonDAO;
import org.springframework.stereotype.Repository;

@Repository
public class AccountingBookDAO extends CommonDAO<AccountingBook> {

	public AccountingBookDAO() {
		super(AccountingBook.class);
	}

}
