package org.solt.wealth.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.solt.wealth.model.view.JournalView;
import org.solt.wealth.persist.IAccountingBookReportDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingBookReportService implements IAccountingBookReportService {

	@Autowired
	private IAccountingBookReportDAO dao;

	public List<Map<String, Object>> findTotalJournalAnnual(Map<String, Object> params) throws ServiceException {
		try {
			return dao.getTotalJournalYearly(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Map<String, Object>> findTotalJournalMothly(Map<String, Object> params) throws ServiceException {
		try {
			return dao.getTotalJournalMothly(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Map<String, Object>> findTotalMothlyByCategory(Map<String, Object> params) throws ServiceException {
		try {
			return dao.getTotalMothlyByCategory(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public String getJournalMinMonthForYear(Map<String, Object> params) throws ServiceException {
		try {
			return dao.getJournalMinMonthForYear(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Map<String, Object>> findPriceHisByItem(Map<String, Object> params) throws ServiceException {
		try {
			return dao.getPriceHisByItem(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<JournalView> findJournalView(Map<String, Object> params) throws ServiceException {
		try {
			return dao.findJournalViewByParam(params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ServiceException("数据库访问异常！");
		}
	}

}
