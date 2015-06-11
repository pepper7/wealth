package org.solt.wealth.persist.common;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.solt.wealth.model.common.Enums;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/app-data-test.xml"})
public class EnumDAOTest {

	@Resource
	private EnumDAO dao;

	@Before
	public void before() {
	}

	@Test
	public void getEnumsById() throws SQLException {
		Enums enums = null;
		//查空记录
		enums = new Enums();
		String enumId = "";
		enums.setEnumId(enumId);
		enums = dao.findByKey(enums);
		Assert.assertNull("失败，该记录应该不存在!",enums);
		
		//查存在记录
		enums = new Enums();
		enumId = "0101";
		enums.setEnumId(enumId);
		enums = dao.findByKey(enums);
		Assert.assertNotNull("失败, 该记录应该存在!",enums);
		Assert.assertEquals(enumId, enums.getEnumId());
	}
}
