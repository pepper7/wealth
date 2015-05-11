package org.solt.wealth.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomepageAction {

	private final static String PAGE_TITLE = "试验场【账簿】";
	
	@RequestMapping(value = "index.htm")
	public ModelAndView toIndexPage(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.setViewName("index");
		return mav;
	}
}
