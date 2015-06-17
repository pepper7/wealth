package org.solt.wealth.web;

import javax.validation.Valid;

import org.solt.wealth.model.common.Enums;
import org.solt.wealth.model.common.Page;
import org.solt.wealth.service.IJournalCategoryService;
import org.solt.wealth.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/account/")
public class CategoryAction {

	private final static String PAGE_TITLE = "试验场【账簿】";
	private final static String PAGE_SUB_MENU = "Category";
	@Autowired
	private IJournalCategoryService service;

	// Page
	@RequestMapping(value = "categorymgr.htm")
	public ModelAndView toIndexPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.setViewName("account/categoryMgr");
		return mav;
	}

	@RequestMapping(value = "newcategory.htm")
	public ModelAndView toNewCategoryPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		mav.setViewName("account/addCategory");
		return mav;
	}

	@RequestMapping(value = "editcategory.htm")
	public ModelAndView toEditCategoryPage(Enums enums) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			enums = service.getJournalCategory(enums);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("category", enums);
		mav.setViewName("account/editCategory");
		return mav;
	}

	// Action
	@RequestMapping(value = "searchcategory.htm")
	public ModelAndView searchCategory(Enums enums,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		if (pageIndex == null || pageIndex < 1) {
			pageIndex = 1;
		}
		if (pageSize == null || pageSize < 1) {
			pageSize = 20;
		}
		Page<Enums> page = new Page<Enums>();
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);

		try {
			page = service.findJournalCategoryPage(enums.toMap(), page);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		mav.addObject("journalCategoryPage", page);
		mav.addObject("param", enums);
		if (page.getItemList().size() == 0) {
			mav.addObject("success", "info");
			mav.addObject("msg", "没有找到相关记录");
		}
		mav.setViewName("account/categoryMgr");
		return mav;
	}

	@RequestMapping(value = "addcategory.htm")
	public ModelAndView addCategory(@Valid @ModelAttribute("category") Enums enums, BindingResult enumsErrors) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		if (enumsErrors.hasErrors()) {
			mav.addObject("category", enums);
			mav.addObject("success", "error");
			mav.addObject("msg", "数据格式不符合！");
			mav.setViewName("account/addCategory");
		} else {
			try {
				if (service.addJournalCategory(enums)) {
					mav.addObject("success", "success");
					mav.addObject("msg", "创建成功！");
					mav.setViewName("redirect:/account/categorymgr.htm");
				} else {
					mav.addObject("category", enums);
					mav.addObject("success", "error");
					mav.addObject("msg", "创建失败！");
					mav.setViewName("account/addCategory");
				}
			} catch (Exception e) {
				mav.addObject("category", enums);
				mav.addObject("success", "error");
				mav.addObject("msg", e.getMessage());
				mav.setViewName("account/addCategory");
			}
		}
		return mav;
	}

	@RequestMapping(value = "savecategory.htm")
	public ModelAndView saveCategory(@Valid @ModelAttribute("category") Enums enums, BindingResult enumsErrors,
			RedirectAttributes modelMap) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		if (enumsErrors.hasErrors()) {
			mav.addObject("category", enums);
			mav.setViewName("account/addCategory");
			mav.addObject("success", "error");
			mav.addObject("msg", "数据格式不符合！");
		} else {
			try {
				if (service.updateJournalCategory(enums)) {
					modelMap.addFlashAttribute("success", "success");
					modelMap.addFlashAttribute("msg", "保存成功！");
					mav.setViewName("redirect:/account/categorymgr.htm");
				} else {
					mav.addObject("category", enums);
					mav.addObject("success", "error");
					mav.addObject("msg", "保存失败！");
					mav.setViewName("account/addCategory");
				}
			} catch (Exception e) {
				mav.addObject("category", enums);
				mav.addObject("success", "error");
				mav.addObject("msg", e.getMessage());
				mav.setViewName("account/addCategory");
			}
		}
		return mav;
	}

	@RequestMapping(value = "deletecategory.htm")
	public ModelAndView deleteCategory(Enums enums, BindingResult enumsErrors, RedirectAttributes modelMap) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.addObject("navMenu", PAGE_SUB_MENU);
		try {
			service.deleteJournalCategory(enums);
			modelMap.addFlashAttribute("success", "success");
			modelMap.addFlashAttribute("msg", "删除成功！");
		} catch (ServiceException e) {
			modelMap.addFlashAttribute("success", "error");
			modelMap.addFlashAttribute("msg", e.getMessage());
		}
		mav.setViewName("redirect:/account/categorymgr.htm");
		return mav;
	}
}
