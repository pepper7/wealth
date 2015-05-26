package org.solt.wealth.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.salt.common.MD5Util;
import org.solt.wealth.model.UserLogin;
import org.solt.wealth.service.IUserLoginService;
import org.solt.wealth.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomepageAction {

	private final static String PAGE_TITLE = "试验场【账簿】";
	@Autowired
	private IUserLoginService service;

	@RequestMapping(value = "index.htm")
	public ModelAndView toIndexPage(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "login.htm")
	public ModelAndView toLoginPage(HttpSession httpSession) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.setViewName("login");
		return mav;
	}

	// Action
	@RequestMapping(value = "userlogin.htm")
	public ModelAndView login(HttpSession httpSession, HttpServletRequest request,
			@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult enumsErrors) {
		ModelAndView mav = new ModelAndView();
		if (enumsErrors.hasErrors()) {
			mav.addObject("userLogin", userLogin);
			mav.addObject("success", "error");
			mav.addObject("msg", "数据格式不符合！");
			mav.setViewName("login");
		} else {
			try {
				userLogin.setPassword(MD5Util.parseStrToMd5L32(userLogin.getPassword(), userLogin.getUserLoginId()));
				UserLogin user = service.getUserLogin(userLogin);
				if (user.getPassword().equals(userLogin.getPassword())) {
					httpSession.setAttribute("userLogin-salt", userLogin);
					mav.setViewName("index");
				} else {
					mav.addObject("success", "error");
					mav.addObject("msg", "账户或密码错误！");
					mav.setViewName("login");
				}
			} catch (ServiceException e) {
				e.printStackTrace();
				mav.setViewName("login");
			}
		}
		return mav;
	}

	@RequestMapping(value = "logout.htm")
	public ModelAndView logout(HttpSession httpSession, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		httpSession.removeAttribute("userLogin-salt");
		mav.setViewName("index");
		return mav;
	}
}
