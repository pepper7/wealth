package org.solt.wealth.persist;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountingBookReportDAO implements IAccountingBookReportDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Map<String, Object>> getTotalJournalYearly(
			Map<String, Object> params) {
		return sqlSession.selectList(
				"org.solt.wealth.model.Journal.getTotalJournalYearly", params);
	}

	public List<Map<String, Object>> getTotalJournalMothly(
			Map<String, Object> params) {
		return sqlSession.selectList(
				"org.solt.wealth.model.Journal.getTotalJournalMothly", params);
	}

	public List<Map<String, Object>> getTotalMothlyByCategory(
			Map<String, Object> params) {
		return sqlSession.selectList(
				"org.solt.wealth.model.Journal.getTotalMainCategoryByMoth",
				params);
	}

	public String getJournalMinMonthForYear(Map<String, Object> params) {
		return sqlSession.selectOne(
				"org.solt.wealth.model.Journal.getJournalMinMonthForYear",
				params);
	}

	public List<Map<String, Object>> getPriceHisByItem(
			Map<String, Object> params) {
		return sqlSession.selectList(
				"org.solt.wealth.model.Journal.getJournalPriceHis", params);
	}

}
