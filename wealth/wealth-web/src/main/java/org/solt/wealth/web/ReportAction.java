package org.solt.wealth.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.solt.wealth.model.AccountingBook;
import org.solt.wealth.model.Journal;
import org.solt.wealth.service.IAccountingBookReportService;
import org.solt.wealth.service.IAccountingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping(value = "/report/")
public class ReportAction {

	@Autowired
	IAccountingBookService service;
	@Autowired
	IAccountingBookReportService reportService;

	// Page
	@RequestMapping(value = "reports.htm")
//	@RecordLog(description="跳转至报表页面")
	public ModelAndView toJournalReportsPage() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("title", "试验场【账簿】");
		result.put("topCategoryList", service.getCategory());
		return new ModelAndView("report/reports", "result", result);
	}
	
	// Action
	@RequestMapping(value = "/getyearlydatas.htm")
//	@RecordLog(description="获得按年统计数据，所有")
	@ResponseBody
	public Map<String,Object> getYearlyDatas(HttpSession httpSession){
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if(null != accBook){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("accBookId", accBook.getAccBookId());
			
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map: reportService.findTotalJournalAnnual(params)){
				map.put("link", "javascript:refreshMonthlyRpt('"+map.get("label").toString()+"');");
				datas.add(map);
			}
			result.put("data", datas);
			
			Map<String,Object> chart = new HashMap<String,Object>();
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
//	@RecordLog(description="获得月统计数据，按年")
	@ResponseBody
	public Map<String,Object> getMonthlyDatas(HttpSession httpSession, 
			@RequestParam(value="year",required = false) String year){
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if(null != accBook){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("accBookId", accBook.getAccBookId());
			if(year == null || "".equals(year.trim())){
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
			params.put("year", year.trim());
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map: reportService.findTotalJournalMothly(params)){
				map.put("link", "javascript:refreshMainCategoryRpt('"+map.get("label").toString()+"');");
				datas.add(map);
			}
			result.put("data", datas);
			
			Map<String,Object> chart = new HashMap<String,Object>();
			chart.put("caption", year+" Monthly Reports");
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
//	@RecordLog(description="获得主类型统计数据，按月")
	@ResponseBody
	public Map<String,Object> getMonthlyDatasByCategory(HttpSession httpSession,
			@RequestParam(value="month",required = false) String month){
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if(null != accBook){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("accBookId", accBook.getAccBookId());
			if(month == null || "".equals(month.trim())){
				month = Calendar.getInstance().get(Calendar.YEAR)+"-"+(Calendar.getInstance().get(Calendar.MONTH)+1);
			}
			params.put("month", month);
			
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			for(Map<String,Object> map: reportService.findTotalMothlyByCategory(params)){
				map.put("link", "javascript:refreshMainCategoryDetailRpt('"+month+"','"+map.get("main_category").toString()+"');");
				datas.add(map);
			}
			
			result.put("data", datas);
			
			Map<String,Object> chart = new HashMap<String,Object>();
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
	public Map<String,Object> getJournalMinMonthForYear(HttpSession httpSession,
			@RequestParam(value="year",required = false) String year){
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if(null != accBook){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("accBookId", accBook.getAccBookId());
			if(year == null || "".equals(year.trim())){
				year = Calendar.getInstance().get(Calendar.YEAR)+"";
			}
			params.put("year", year.trim());
			
			result.put("month", reportService.getJournalMinMonthForYear(params));
		}
		result.put("success", true);
		return result;
	}
	
	@RequestMapping(value = "/getpricehisdatas.htm")
//	@RecordLog(description="获得历史价格数据")
	@ResponseBody
	public Map<String,Object> getHistoryPriceDatas(HttpSession httpSession, 
			@RequestParam(value="item",required = false) String item){
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if(null != accBook){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("accBookId", accBook.getAccBookId());
			if(item == null || "".equals(item.trim())){
				item = "#$*";
			}
			params.put("item", item.trim());
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			datas = reportService.findPriceHisByItem(params);
			result.put("data", datas);
			
			Map<String,Object> chart = new HashMap<String,Object>();
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
//	@RecordLog(description="获得按月主分类记录明细")
	@ResponseBody
	public Map<String,Object> getMonthlyCategoryDetails(HttpSession httpSession, 
			@RequestParam(value="month",required = false) String month,
			@RequestParam(value="category",required = false) String category){
		Map<String, Object> result = new HashMap<String, Object>();
		AccountingBook accBook = (AccountingBook) httpSession.getAttribute("currentAccBook");
		if(null != accBook){
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("accBookId", accBook.getAccBookId());
			if(category == null || "".equals(category.trim())){
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
