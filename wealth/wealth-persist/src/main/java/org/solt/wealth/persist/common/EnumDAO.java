package org.solt.wealth.persist.common;

import java.util.List;

import org.solt.wealth.model.common.Enums;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class EnumDAO extends CommonDAO<Enums> {

	/**
	 * @see IEnumDAO.findEnumsByParamForLayer
	 */
	public List<Enums> findEnumsByParamForLayer(Enums enums) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.common.Enums.findEnumsByParamForLayer", enums);
	}

}
