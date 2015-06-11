package org.solt.wealth.persist;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.solt.wealth.model.Journal;
import org.solt.wealth.model.view.JournalView;
import org.solt.wealth.persist.common.CommonDAO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public class JournalDAO extends CommonDAO<Journal> {

	private final Logger logger = Logger.getLogger(JournalDAO.class);

	public JournalDAO() {
		super(Journal.class);
	}

	public Double getTotalJournal(Map<String, Object> params) throws DataAccessException {
		logger.debug(">>>JournalDAO.getTotalJournal(params=" + params + ")");
		return sqlSession.selectOne("org.solt.wealth.model.Journal.getTotalJournal", params);
	}

	public List<Map<String, Object>> getTotalJournalYearly(Map<String, Object> params) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.Journal.getTotalJournalYearly", params);
	}

	public List<Map<String, Object>> getTotalJournalMothly(Map<String, Object> params) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.Journal.getTotalJournalMothly", params);
	}

	public List<Map<String, Object>> getTotalMothlyByCategory(Map<String, Object> params) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.Journal.getTotalMainCategoryByMoth", params);
	}

	public String getJournalMinMonthForYear(Map<String, Object> params) throws DataAccessException {
		return sqlSession.selectOne("org.solt.wealth.model.Journal.getJournalMinMonthForYear", params);
	}

	public List<Map<String, Object>> getPriceHisByItem(Map<String, Object> params) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.Journal.getJournalPriceHis", params);
	}

	public List<JournalView> findJournalViewByParam(Map<String, Object> params) throws DataAccessException {
		logger.debug(">>>JournalDAO.findJournalViewByParam(params=" + params + ")");
		return sqlSession.selectList("org.solt.wealth.model.ReportAcc.findJournalViewByParam", params);
	}

}
