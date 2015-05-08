package org.solt.wealth.persist.common;

import java.sql.SQLException;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.solt.wealth.model.common.Enums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EnumDAO implements IEnumDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Enums getEnumsById(Enums enums) {
		return (Enums) sqlSession.selectOne("org.solt.wealth.model.common.Enums.getEnums", enums);
	}

	public List<Enums> findEnumsByParam(Enums enums) {
		return sqlSession.selectList("org.solt.wealth.model.common.Enums.findEnumsByParam", enums);
	}

	public int insertEnums(Enums enums) throws SQLException {
		return sqlSession.insert("org.solt.wealth.model.common.Enums.insertEnums", enums);
	}

	public int updateEnums(Enums enums) {
		return sqlSession.update("org.solt.wealth.model.common.Enums.updateEnums", enums);
	}

	public int deleteEnums(Enums enums) {
		return sqlSession.delete("org.solt.wealth.model.common.Enums.deleteEnums", enums);
	}

	/**
	 * @see IEnumDAO.findEnumsByParamForLayer
	 */
	public List<Enums> findEnumsByParamForLayer(Enums enums) {
		return sqlSession.selectList("org.solt.wealth.model.common.Enums.findEnumsByParamForLayer", enums);
	}

}
