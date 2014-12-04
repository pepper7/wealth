package org.solt.wealth.service;

import java.util.List;
import java.util.Map;

import org.solt.wealth.persist.IAccountingBookReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingBookReportService implements
		IAccountingBookReportService {

	@Autowired
	private IAccountingBookReportDAO dao;

	public List<Map<String, Object>> findTotalJournalAnnual(
			Map<String, Object> params) {
		return dao.getTotalJournalYearly(params);
	}

	public List<Map<String, Object>> findTotalJournalMothly(
			Map<String, Object> params) {
		return dao.getTotalJournalMothly(params);
	}

	public List<Map<String, Object>> findTotalMothlyByCategory(
			Map<String, Object> params) {
		return dao.getTotalMothlyByCategory(params);
	}

	public String getJournalMinMonthForYear(Map<String, Object> params) {
		return dao.getJournalMinMonthForYear(params);
	}

	public List<Map<String, Object>> findPriceHisByItem(
			Map<String, Object> params) {
		return dao.getPriceHisByItem(params);
	}

}
