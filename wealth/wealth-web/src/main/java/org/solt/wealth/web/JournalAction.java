package org.solt.wealth.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import org.solt.wealth.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/account/")
public class JournalAction {

	private final static String PAGE_TITLE = "试验场【账簿】";
	private final static String PAGE_SUB_MENU = "Journal";
	private static final Logger logger = LoggerFactory.getLogger(JournalAction.class);

	@Autowired
	private IAccountingBookService service;
	@Autowired
	private IJournalCategoryService categoryService;
	@Autowired
	private IAccountingBookReportService reportService;

	// Page
	@RequestMapping(value = "journal.htm")
	// @RecordLog(description = "跳转至账簿试验场首页")
	public ModelAndView toIndexPage(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String month = dateFormat.format(new Date());
		try {
			mav.addObject("accBookList", service.findAccounts(null));
		} catch (ServiceException e) {
		}
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");

		if (null != accBook) {
			// 获得日记账
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("month", month);
			params.put("accBookId", accBook.getAccBookId());
			try {
				mav.addObject("journalList", reportService.findJournalView(params));
				mav.addObject("total", service.totalJournal(params));
			} catch (ServiceException e) {
			}
			mav.addObject("month", month);
		}
		mav.setViewName("account/journal");
		return mav;
	}

	@RequestMapping(value = "newjournal.htm")
	// @RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toNewJournalPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		Enums enums = new Enums();
		try {
			mav.addObject("topCategoryList", categoryService.findJournalCategoryForLayer(enums));
		} catch (ServiceException e) {
		}
		mav.setViewName("account/addJournal");
		return mav;
	}

	@RequestMapping(value = "editjournal.htm")
	// @RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toEditJournalPage(Journal journal) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		Enums enums = new Enums();
		try {
			mav.addObject("topCategoryList", categoryService.findJournalCategoryForLayer(enums));
			journal = service.getJournal(journal);
			if (null != journal.getTopCategory()) {
				enums.setParentEnumId(journal.getTopCategory());
				mav.addObject("mainCategoryList", categoryService.findJournalCategoryForLayer(enums));
			}
			if (null != journal.getMainCategory()) {
				enums.setParentEnumId(journal.getMainCategory());
				mav.addObject("subCategoryList", categoryService.findJournalCategoryForLayer(enums));
			}
		} catch (ServiceException e) {
		}
		mav.addObject("journal", journal);
		mav.setViewName("account/editJournal");
		return mav;
	}

	// Action
	@RequestMapping(value = "chooseaccbook.htm")
	public ModelAndView chooseAccountingBook(AccountingBook accountingBook, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			accountingBook = service.getAccount(accountingBook);
		} catch (ServiceException e) {
		}
		httpSession.setAttribute("currentAccBook", accountingBook);
		mav.addObject("currentAccBook", accountingBook);
		mav.setViewName("redirect:/account/journal.htm");
		return mav;
	}

	@RequestMapping(value = "addjournal.htm")
	public ModelAndView addJournal(Journal journal, @RequestParam("journalDatetime") String journalDatetime) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);

		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		if (null != journalDatetime) {
			// try {
			// java.util.Date date = dateFormat.parse(journalDatetime);
			journal.setJournalDate(java.sql.Date.valueOf(journalDatetime));
			// } catch (ParseException e) {
			// e.printStackTrace();
			// return new ModelAndView("account/addJournal", "result", result);
			// }
			try {
				if (service.addJournal(journal)) {
					mav.setViewName("redirect:/account/journal.htm");
				} else {
					mav.setViewName("account/addJournal");
				}
			} catch (ServiceException e) {
			}
		} else {
			mav.setViewName("account/addJournal");
		}

		return mav;
	}

	@RequestMapping(value = "savejournal.htm")
	public ModelAndView saveJournal(Journal journal, @RequestParam("journalDatetime") String journalDatetime) {
		// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.addObject("journal", journal);

		if (null != journalDatetime) {
			// try {
			// java.util.Date date = dateFormat.parse(journalDatetime);
			journal.setJournalDate(java.sql.Date.valueOf(journalDatetime));
			// } catch (ParseException e) {
			// e.printStackTrace();
			// return new ModelAndView("account/addJournal", "result", result);
			// }
		} else {
			mav.setViewName("account/editJournal");
		}
		try {
			if (service.saveJournal(journal)) {
				mav.setViewName("redirect:/account/journal.htm");
			} else {
				mav.setViewName("account/editJournal");
			}
		} catch (ServiceException e) {
		}
		return mav;
	}

	@RequestMapping(value = "deletejournal.htm")
	public ModelAndView deleteJournal(Journal journal) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.setViewName("redirect:/account/jounral.htm");
		try {
			service.deleteJournal(journal);
		} catch (ServiceException e) {
		}
		return mav;
	}

	@RequestMapping(value = "searchjournal.htm")
	public ModelAndView searchJournal(Journal journal, @RequestParam("searchMonth") String month) {
		logger.debug(">>>org.solt.wealth.web.JournalAction.searchJournal(journal=,month=" + month + ")");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("month", month);
		params.put("accBookId", journal.getAccBookId());
		try {
			mav.addObject("journalList", reportService.findJournalView(params));
			mav.addObject("total", service.totalJournal(params));
		} catch (ServiceException e) {
		}
		mav.addObject("month", month);
		// others
		try {
			mav.addObject("accBookList", service.findAccounts(null));
		} catch (ServiceException e) {
		}
		mav.setViewName("account/journal");
		return mav;
	}

	@RequestMapping(value = "getCategories.htm")
	@ResponseBody
	public Map<String, Object> getCategories(Enums enums) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (null == enums.getEnumId()) {
				result.put("categoryList", categoryService.findJournalCategoryForLayer(enums));
			} else {
				result.put("categoryList", categoryService.findJournalCategory(enums));
			}
		} catch (ServiceException e) {
			result.put("success", false);
		}
		result.put("success", true);
		return result;
	}

}
