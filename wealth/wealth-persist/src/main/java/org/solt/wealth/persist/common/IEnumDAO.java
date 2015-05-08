package org.solt.wealth.persist.common;

import java.sql.SQLException;
import java.util.List;

import org.solt.wealth.model.common.Enums;

public interface IEnumDAO {

	Enums getEnumsById(Enums enums);

	List<Enums> findEnumsByParam(Enums enums);

	int insertEnums(Enums enums) throws SQLException;

	int updateEnums(Enums enums);

	int deleteEnums(Enums enums);
	
	/**
	 * 按父类层级及其他参数查询
	 * @param enums 分类查询参数
	 * @return 分类列表
	 */
	List<Enums> findEnumsByParamForLayer(Enums enums);
}
