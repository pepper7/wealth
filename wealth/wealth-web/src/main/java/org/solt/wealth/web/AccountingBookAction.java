package org.solt.wealth.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;
import org.solt.wealth.model.common.Enums;
import org.solt.wealth.service.IAccountingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping(value = "/account/")
public class AccountingBookAction {

	@Autowired
	IAccountingBookService service;

	// Page
	@RequestMapping(value = "index.htm")
//	@RecordLog(description = "跳转至账簿试验场首页")
	public ModelAndView toIndexPage(HttpSession httpSession) {
		Map<String, Object> result = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String month = dateFormat.format(new Date());
		result.put("title", "试验场【账簿】");
		result.put("accBookList", service.findAccounts(null));
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		
		if(null != accBook){
			//获得日记账
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("month", month);
			params.put("accBookId", accBook.getAccBookId());
			result.put("journalList", service.findJournal(params));
			result.put("total", service.totalJournal(params));
			result.put("month", month);
		}
		return new ModelAndView("account/index", "result", result);
	}
	
	@RequestMapping(value = "newaccbook.htm")
//	@RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toNewAccountPage() {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("title", "试验场【账簿】");
		mav.addObject("result",result);
		mav.setViewName("account/addAccBook");
		return mav;
	}
	
	@RequestMapping(value = "newjournal.htm")
//	@RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toNewJournalPage() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("title", "试验场【账簿】");
		result.put("topCategoryList", service.getCategory());
		return new ModelAndView("account/addJournal", "result", result);
	}
	
	@RequestMapping(value = "editjournal.htm")
//	@RecordLog(description="跳转至账簿创建页面")
	public ModelAndView toEditJournalPage(Journal journal) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "试验场【账簿】");
		mav.addObject("topCategoryList", service.getCategory());
		journal = service.getJournal(journal);
		Enums enums = new Enums();
		if(null != journal.getTopCategory()){
			enums.setParentEnumId(journal.getTopCategory());
			mav.addObject("mainCategoryList", service.getCategory(enums));
		}
		if(null != journal.getMainCategory()){
			enums.setParentEnumId(journal.getMainCategory());
			mav.addObject("subCategoryList", service.getCategory(enums));
		}
		mav.addObject("journal", journal);
		mav.setViewName("account/editJournal");
		return mav;
	}
		
	// Action
	@RequestMapping(value="addaccbook.htm")
	public ModelAndView addAccountingBook(AccountingBook accountingBook){
		Map<String, Object> result = new HashMap<String, Object>();
		if(service.addAccount(accountingBook)){
			return new ModelAndView("redirect:/index.htm");
		}else{
			return new ModelAndView("account/addAccBook", "result", result);
		}
	}
	
	@RequestMapping(value="chooseaccbook.htm")
	public ModelAndView chooseAccountingBook(AccountingBook accountingBook, HttpSession httpSession){
		Map<String, Object> result = new HashMap<String, Object>();
		accountingBook = service.getAccount(accountingBook);
		httpSession.setAttribute("currentAccBook", accountingBook);
		result.put("title", "试验场【账簿】");
		result.put("currentAccBook", accountingBook);
		return new ModelAndView("redirect:/index.htm","result", result);
	}
	
	@RequestMapping(value="addjournal.htm")
	public ModelAndView addJournal(Journal journal,@RequestParam("journalDatetime") String journalDatetime){
		Map<String, Object> result = new HashMap<String, Object>();
		
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		if(null != journalDatetime){
//			try {
				//java.util.Date date = dateFormat.parse(journalDatetime);
				journal.setJournalDate(java.sql.Date.valueOf(journalDatetime));
//			} catch (ParseException e) {
//				e.printStackTrace();
//				return new ModelAndView("account/addJournal", "result", result);
//			}
		}else{
			return new ModelAndView("account/addJournal", "result", result);
		}
		
		if(service.addJournal(journal)){
			return new ModelAndView("redirect:/index.htm");
		}else{
			return new ModelAndView("account/addJournal", "result", result);
		}
	}
	
	@RequestMapping(value="savejournal.htm")
	public ModelAndView saveJournal(Journal journal,@RequestParam("journalDatetime") String journalDatetime){
		//SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "试验场【账簿】");
		mav.addObject("journal", journal);
		
		if(null != journalDatetime){
//			try {
				//java.util.Date date = dateFormat.parse(journalDatetime);
				journal.setJournalDate(java.sql.Date.valueOf(journalDatetime));
//			} catch (ParseException e) {
//				e.printStackTrace();
//				return new ModelAndView("account/addJournal", "result", result);
//			}
		}else{
			mav.setViewName("account/editJournal");
		}
		
		if(service.saveJournal(journal)){
			mav.setViewName("redirect:/index.htm");
		}else{
			mav.setViewName("account/editJournal");
		}
		return mav;
	}
	
	@RequestMapping(value="deletejournal.htm")
	public ModelAndView deleteJournal(Journal journal){
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "试验场【账簿】");
		mav.setViewName("redirect:/index.htm");
		service.deleteJournal(journal);
		return mav;
	}
	
	@RequestMapping(value="searchJournal.htm")
	public ModelAndView searchJournal(Journal journal, @RequestParam("searchMonth") String month){
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("month", month);
		params.put("accBookId", journal.getAccBookId());
		result.put("journalList", service.findJournal(params));
		result.put("total", service.totalJournal(params));
		result.put("month", month);
		//others
		result.put("title", "试验场【账簿】");
		result.put("accBookList", service.findAccounts(null));
		return new ModelAndView("account/index", "result", result); 
	}
	
	@RequestMapping(value = "getCategories.htm")
	@ResponseBody
	public Map<String,Object> getCategories(Enums enums){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("categoryList", service.getCategory(enums));
		result.put("success", true);
		return result;
	}
}
