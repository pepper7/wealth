package org.solt.wealth.persist;

import java.util.List;
import java.util.Map;

public interface IAccountingBookReportDAO {
	
	List<Map<String, Object>> getTotalJournalYearly(Map<String, Object> params);
	List<Map<String, Object>> getTotalJournalMothly(Map<String, Object> params);
	List<Map<String, Object>> getTotalMothlyByCategory(Map<String, Object> params);
	
	List<Map<String, Object>> getPriceHisByItem(Map<String, Object> params);
	
	String getJournalMinMonthForYear(Map<String, Object> params);
}
