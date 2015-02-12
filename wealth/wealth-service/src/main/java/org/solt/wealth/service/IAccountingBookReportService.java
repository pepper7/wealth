package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.model.view.JournalView;

public interface IAccountingBookReportService {
	public List<Map<String, Object>> findTotalJournalAnnual(Map<String, Object> params);
	public List<Map<String, Object>> findTotalJournalMothly(Map<String, Object> params);
	public List<Map<String, Object>> findTotalMothlyByCategory(Map<String, Object> params);
	
	public List<Map<String, Object>> findPriceHisByItem(Map<String, Object> params);
	
	public String getJournalMinMonthForYear(Map<String, Object> params);
	
	public List<JournalView> findJournalView(Map<String, Object> params);
}
