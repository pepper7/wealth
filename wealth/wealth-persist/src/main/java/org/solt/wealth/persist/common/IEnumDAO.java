package org.solt.wealth.persist.common;

import java.util.List;

import org.solt.wealth.model.common.Enums;
import org.springframework.dao.DataAccessException;

public interface IEnumDAO {

	Enums getEnumsById(Enums enums) throws DataAccessException;

	List<Enums> findEnumsByParam(Enums enums) throws DataAccessException;

	int insertEnums(Enums enums) throws DataAccessException;

	int updateEnums(Enums enums) throws DataAccessException;

	int deleteEnums(Enums enums) throws DataAccessException;

	/**
	 * 按父类层级及其他参数查询
	 * @param enums 分类查询参数
	 * @return 分类列表
	 */
	List<Enums> findEnumsByParamForLayer(Enums enums) throws DataAccessException;
}
