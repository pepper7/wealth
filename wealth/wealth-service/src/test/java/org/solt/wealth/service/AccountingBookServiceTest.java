package org.solt.wealth.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.service.IAccountingBookService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContext-test.xml"})
public class AccountingBookServiceTest {

	@Resource
	private IAccountingBookService service;

	@Test
	public void getAccount() throws ServiceException {
		AccountingBook acc = null;
		//
		acc = new AccountingBook();
		Integer accBookId = 0;
		acc.setAccBookId(accBookId);
		acc = service.getAccount(acc);
		Assert.assertNull("失败，该记录应该不存在!", acc);

		//
		acc = new AccountingBook();
		accBookId = 1;
		acc.setAccBookId(accBookId);
		acc = service.getAccount(acc);
		Assert.assertNotNull("失败，该记录应该存在!", acc);
		Assert.assertEquals(accBookId, acc.getAccBookId());
	}
}
