package org.solt.wealth.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.service.IAccountingBookReportService;
import org.solt.wealth.service.IAccountingBookService;
import org.solt.wealth.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/account/")
public class AccountingBookAction {

	private final static String PAGE_TITLE = "试验场【账簿】";
	private final static String PAGE_SUB_MENU = "AccBook";

	@Autowired
	private IAccountingBookService service;
	@Autowired
	private IAccountingBookReportService reportService;

	// Page
	@RequestMapping(value = "index.htm")
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
			e.printStackTrace();
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
				e.printStackTrace();
			}
			mav.addObject("month", month);
		}
		mav.setViewName("account/index");
		return mav;
	}

	@RequestMapping(value = "newaccbook.htm")
	// @RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toNewAccountPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.setViewName("account/addAccBook");
		return mav;
	}

	@RequestMapping(value = "editaccbook.htm")
	// @RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toEditJournalPage(AccountingBook accountingBook) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			accountingBook = service.getAccount(accountingBook);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("accountingBook", accountingBook);
		mav.setViewName("account/editAccBook");
		return mav;
	}

	// Action
	@RequestMapping(value = "addaccbook.htm")
	public ModelAndView addAccountingBook(AccountingBook accountingBook) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			if (service.addAccount(accountingBook)) {
				mav.setViewName("redirect:/account/index.htm");
			} else {
				mav.setViewName("account/addAccBook");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "saveaccbook.htm")
	public ModelAndView saveAccountingBook(AccountingBook accountingBook) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			if (service.updateAccount(accountingBook)) {
				mav.setViewName("redirect:/account/index.htm");
			} else {
				mav.setViewName("account/addAccBook");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "searchaccbook.htm")
	public ModelAndView searchAccountingBook(AccountingBook accountingBook) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.addObject("param", accountingBook);
		List<AccountingBook> list = null;
		try {
			list = service.findAccounts(accountingBook);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("accbookList", list);
		if (list.size() == 0) {
			mav.addObject("success", "info");
			mav.addObject("msg", "没有找到相关记录");
		}
		mav.setViewName("account/index");
		return mav;
	}

	@RequestMapping(value = "deleteaccbook.htm")
	public ModelAndView deleteAccountingBook(AccountingBook accountingBook, RedirectAttributes modelMap) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			service.deleteAccount(accountingBook);
			modelMap.addFlashAttribute("success", "success");
			modelMap.addFlashAttribute("msg", "删除成功！");
		} catch (ServiceException e) {
			modelMap.addFlashAttribute("success", "error");
			modelMap.addFlashAttribute("msg", e.getMessage());
		}
		mav.setViewName("redirect:/account/searchaccbook.htm");
		return mav;
	}
}
