package org.solt.wealth.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;
import org.solt.wealth.model.common.Enums;
import org.solt.wealth.service.IAccountingBookReportService;
import org.solt.wealth.service.IAccountingBookService;
import org.solt.wealth.service.IJournalCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
// @RequestMapping(value = "/report/")
public class ReportAction {

	private final static String PAGE_TITLE = "试验场【账簿】";
	private final static String PAGE_SUB_MENU = "Report";
	private static final Logger logger = LoggerFactory.getLogger(ReportAction.class);
	@Autowired
	private IAccountingBookService service;
	@Autowired
	private IJournalCategoryService categoryService;
	@Autowired
	private IAccountingBookReportService reportService;

	// Page
	@RequestMapping(value = "reports.htm")
	// @RecordLog(description="跳转至报表页面")
	public ModelAndView toJournalReportsPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.addObject("accBookList", service.findAccounts(null));
		Enums enums = new Enums();
		mav.addObject("topCategoryList", categoryService.findJournalCategoryForLayer(enums));
		mav.setViewName("report/reports");
		return mav;
	}

	@RequestMapping("journalMonthlyPdf.htm")
	public ModelAndView journalMonthlyPdf(HttpSession httpSession,
			@RequestParam(value = "month", required = false) String month) {
		logger.debug(">>>org.solt.wealth.web.ReportAction.journalMonthlyPdf()");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("reportMonthlyPdf");
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		System.err.println("AccountingBook="+accBook);
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());
			if (month == null || "".equals(month.trim())) {
				month = Calendar.getInstance().get(Calendar.YEAR) + "-"
						+ (Calendar.getInstance().get(Calendar.MONTH) + 1);
			}
			params.put("month", month);
			params.put("orderList", "journal_date");
			mav.addObject("month", month);
			mav.addObject("accBook", accBook);
			mav.addObject("journalList", service.findJournal(params));
			mav.addObject("total", service.totalJournal(params));
		}
		return mav;
	}

	// Action
	@RequestMapping(value = "chooseaccbook.htm")
	public ModelAndView chooseAccountingBook(AccountingBook accountingBook, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		accountingBook = service.getAccount(accountingBook);
		httpSession.setAttribute("currentAccBook", accountingBook);
		mav.addObject("currentAccBook", accountingBook);
		mav.setViewName("redirect:/reports.htm");
		return mav;
	}
	
	@RequestMapping(value = "/getyearlydatas.htm")
	// @RecordLog(description="获得按年统计数据，所有")
	@ResponseBody
	public Map<String, Object> getYearlyDatas(HttpSession httpSession) {
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());

			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : reportService.findTotalJournalAnnual(params)) {
				map.put("link", "javascript:refreshMonthlyRpt('" + map.get("label").toString() + "');");
				datas.add(map);
			}
			result.put("data", datas);

			Map<String, Object> chart = new HashMap<String, Object>();
			chart.put("caption", "Yearly Reports");
			chart.put("useroundedges", "1");
			chart.put("showborder", "0");
			chart.put("xaxisname", "Year");
			chart.put("yaxisname", "Amount");
			result.put("chart", chart);
		}
		result.put("success", true);
		return result;
	}

	@RequestMapping(value = "/getmonthlydatas.htm")
	// @RecordLog(description="获得月统计数据，按年")
	@ResponseBody
	public Map<String, Object> getMonthlyDatas(HttpSession httpSession,
			@RequestParam(value = "year", required = false) String year) {
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());
			if (year == null || "".equals(year.trim())) {
				year = Calendar.getInstance().get(Calendar.YEAR) + "";
			}
			params.put("year", year.trim());
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : reportService.findTotalJournalMothly(params)) {
				map.put("link", "javascript:refreshMainCategoryRpt('" + map.get("label").toString() + "');");
				datas.add(map);
			}
			result.put("data", datas);

			Map<String, Object> chart = new HashMap<String, Object>();
			chart.put("caption", year + " Monthly Reports");
			chart.put("useroundedges", "1");
			chart.put("showborder", "0");
			chart.put("xaxisname", "Month");
			chart.put("yaxisname", "Amount");
			result.put("chart", chart);
		}
		result.put("success", true);
		return result;
	}

	@RequestMapping(value = "/getmothlydatascate.htm")
	// @RecordLog(description="获得主类型统计数据，按月")
	@ResponseBody
	public Map<String, Object> getMonthlyDatasByCategory(HttpSession httpSession,
			@RequestParam(value = "month", required = false) String month) {
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());
			if (month == null || "".equals(month.trim())) {
				month = Calendar.getInstance().get(Calendar.YEAR) + "-"
						+ (Calendar.getInstance().get(Calendar.MONTH) > 8 ? "" : "0")
						+ (Calendar.getInstance().get(Calendar.MONTH) + 1);
			}
			params.put("month", month);

			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			for (Map<String, Object> map : reportService.findTotalMothlyByCategory(params)) {
				map.put("link", "javascript:refreshMainCategoryDetailRpt('" + month + "','"
						+ map.get("main_category").toString() + "');");
				datas.add(map);
			}

			result.put("data", datas);

			Map<String, Object> chart = new HashMap<String, Object>();
			chart.put("caption", month + " Main Category Reports");
			chart.put("useroundedges", "1");
			chart.put("showborder", "0");
			chart.put("showlegend", "1");
			chart.put("legendposition", "RIGHT");
			result.put("chart", chart);
		}
		result.put("success", true);
		return result;
	}

	@RequestMapping(value = "getjournalminmonthforyear.htm")
	@ResponseBody
	public Map<String, Object> getJournalMinMonthForYear(HttpSession httpSession,
			@RequestParam(value = "year", required = false) String year) {
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());
			if (year == null || "".equals(year.trim())) {
				year = Calendar.getInstance().get(Calendar.YEAR) + "";
			}
			params.put("year", year.trim());

			result.put("month", reportService.getJournalMinMonthForYear(params));
		}
		result.put("success", true);
		return result;
	}

	@RequestMapping(value = "/getpricehisdatas.htm")
	// @RecordLog(description="获得历史价格数据")
	@ResponseBody
	public Map<String, Object> getHistoryPriceDatas(HttpSession httpSession,
			@RequestParam(value = "item", required = false) String item) {
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());
			if (item == null || "".equals(item.trim())) {
				item = "#$*";
			}
			params.put("item", item.trim());
			List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
			datas = reportService.findPriceHisByItem(params);
			result.put("data", datas);

			Map<String, Object> chart = new HashMap<String, Object>();
			chart.put("caption", "History Price Reports");
			chart.put("showborder", "0");
			chart.put("xaxisname", "Date");
			chart.put("yaxisname", "Amount");
			chart.put("linecolor", "FF5904");
			result.put("chart", chart);
		}
		result.put("success", true);
		return result;
	}

	@RequestMapping(value = "/getmothlycatedetails.htm")
	// @RecordLog(description="获得按月主分类记录明细")
	@ResponseBody
	public Map<String, Object> getMonthlyCategoryDetails(HttpSession httpSession,
			@RequestParam(value = "month", required = false) String month,
			@RequestParam(value = "category", required = false) String category) {
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if (null != accBook) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("accBookId", accBook.getAccBookId());
			if (category == null || "".equals(category.trim())) {
				category = "#$*";
			}
			params.put("month", month);
			params.put("mainCategory", category);
			List<Journal> datas = new ArrayList<Journal>();
			datas = service.findJournal(params);
			result.put("details", datas);
		}
		result.put("success", true);
		return result;
	}
}
