package org.solt.wealth.persist;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.solt.wealth.model.view.JournalView;

public interface IAccountingBookReportDAO {

	List<Map<String, Object>> getTotalJournalYearly(Map<String, Object> params) throws SQLException;

	List<Map<String, Object>> getTotalJournalMothly(Map<String, Object> params) throws SQLException;

	List<Map<String, Object>> getTotalMothlyByCategory(Map<String, Object> params) throws SQLException;

	List<Map<String, Object>> getPriceHisByItem(Map<String, Object> params) throws SQLException;

	String getJournalMinMonthForYear(Map<String, Object> params) throws SQLException;

	List<JournalView> findJournalViewByParam(Map<String, Object> params) throws SQLException;
}
