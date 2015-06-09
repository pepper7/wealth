package org.solt.wealth.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.salt.common.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(HomepageAction.class);
	@Autowired
	private IUserLoginService service;

	@RequestMapping(value = "index.htm")
	public ModelAndView toIndexPage(HttpSession httpSession) {
		logger.debug(">>>HomepageAction.toIndexPage()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.setViewName("index");
		logger.debug("<<<HomepageAction.toIndexPage()");
		return mav;
	}

	@RequestMapping(value = "login.htm")
	public ModelAndView toLoginPage(HttpSession httpSession) {
		logger.debug(">>>HomepageAction.toLoginPage()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.setViewName("login");
		logger.debug("<<<HomepageAction.toLoginPage()");
		return mav;
	}

	// Action
	@RequestMapping(value = "userlogin.htm")
	public ModelAndView login(HttpSession httpSession, HttpServletRequest request,
			@Valid @ModelAttribute("userLogin") UserLogin userLogin, BindingResult enumsErrors) {
		logger.debug(">>>HomepageAction.login(userLogin=" + userLogin + ")");
		ModelAndView mav = new ModelAndView();
		try {
			if (enumsErrors.hasErrors()) {
				throw new ValidateException("数据格式不符合！");
			} else {
				userLogin.setPassword(MD5Util.parseStrToMd5L32(userLogin.getPassword(), userLogin.getUserLoginId()));
				UserLogin user = service.login(userLogin);
				httpSession.setAttribute("userLogin-salt", user);
				mav.setViewName("redirect:index.htm");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			mav.addObject("userLogin", userLogin);
			mav.addObject("title", PAGE_TITLE);
			mav.addObject("success", "error");
			mav.setViewName("login");
		} catch (ValidateException e) {
			mav.addObject("userLogin", userLogin);
			mav.addObject("title", PAGE_TITLE);
			mav.addObject("success", "error");
			mav.setViewName("login");
		}
		logger.debug("<<<HomepageAction.login()");
		return mav;
	}

	@RequestMapping(value = "logout.htm")
	public ModelAndView logout(HttpSession httpSession, HttpServletRequest request) {
		logger.debug(">>>HomepageAction.logout()");
		ModelAndView mav = new ModelAndView();
		httpSession.removeAttribute("userLogin-salt");
		mav.setViewName("redirect:index.htm");
		logger.debug("<<<HomepageAction.logout()");
		return mav;
	}
}
