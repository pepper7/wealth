package org.solt.wealth.persist;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.persist.IAccountingBookDAO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/app-data-test.xml"})
public class AccountingBookDAOTest {
	@Resource
	private IAccountingBookDAO dao;
	
	@Test
	public void getAccountingBookById(){
		AccountingBook acc = null;
		//查空记录
		acc = new AccountingBook();
		Integer accBookId = 0;
		acc.setAccBookId(accBookId);
		acc = dao.getAccountingBookById(acc);
		Assert.assertNull("失败，该记录应该不存在!",acc);
		
		//查存在记录
		acc = new AccountingBook();
		accBookId = 1;
		acc.setAccBookId(accBookId);
		acc = dao.getAccountingBookById(acc);
		Assert.assertNotNull("失败, 该记录应该存在!",acc);
		Assert.assertEquals(accBookId, acc.getAccBookId());
	}
}
