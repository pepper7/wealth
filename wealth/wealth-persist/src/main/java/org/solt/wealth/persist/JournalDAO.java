package org.solt.wealth.persist;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.solt.wealth.model.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JournalDAO implements IJournalDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private final Logger logger = Logger.getLogger(JournalDAO.class);

	public Journal getJournalById(Journal journal) {
		return (Journal) sqlSession.selectOne(
				"org.solt.wealth.model.Journal.getJournal", journal);
	}

	public List<Journal> findJournal(Journal journal) {
		return sqlSession.selectList(
				"org.solt.wealth.model.Journal.findJournal", journal);
	}

	public int insertJournal(Journal journal) {
		logger.debug(">>>JournalDAO.insertJournal()");
		return sqlSession.insert(
				"org.solt.wealth.model.Journal.insertJournal", journal);
	}

	public int updateJournal(Journal journal) {
		logger.debug(">>>JournalDAO.updateJournal(journal="+journal+")");
		return sqlSession.update(
				"org.solt.wealth.model.Journal.updateJournal", journal);
	}

	public int deleteJournal(Journal journal) {
		return sqlSession.delete(
				"org.solt.wealth.model.Journal.deleteJournal", journal);
	}

	public List<Journal> findJournalByParam(Map<String, Object> params) {
		logger.debug(">>>JournalDAO.findJournalByParam(params="+params+")");
		return sqlSession.selectList(
				"org.solt.wealth.model.Journal.findJournalByParam", params);
	}

	public Double getTotalJournal(Map<String, Object> params) {
		logger.debug(">>>JournalDAO.getTotalJournal(params="+params+")");
		return sqlSession.selectOne(
				"org.solt.wealth.model.Journal.getTotalJournal", params);
	}
	
}
