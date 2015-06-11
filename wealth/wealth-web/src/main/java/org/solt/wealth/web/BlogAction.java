package org.solt.wealth.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solt.wealth.model.Blog;
import org.solt.wealth.service.IBlogService;
import org.solt.wealth.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/blog/")
public class BlogAction {

	private final static String PAGE_TITLE = "试验场【博客】";
	private static final Logger logger = LoggerFactory.getLogger(BlogAction.class);
	@Autowired
	private IBlogService service;

	// Page
	@RequestMapping(value = "index.htm")
	public ModelAndView toIndexPage(HttpSession httpSession) {
		logger.debug(">>>BlogAction.toIndexPage()");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		Blog blog = new Blog();
		List<Blog> list = null;
		try {
			list = service.findBlogs(blog);
		} catch (ServiceException e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}
		mav.addObject("blogList", list);
		if (list == null || list.size() == 0) {
			mav.addObject("success", "info");
			mav.addObject("msg", "没有找到相关记录");
		}
		mav.setViewName("blog/index");
		logger.debug("<<<BlogAction.toIndexPage()");
		return mav;
	}

	@RequestMapping(value = "newblog.htm")
	public ModelAndView toNewBlogPage() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		mav.setViewName("blog/addBlog");
		return mav;
	}
	
	@RequestMapping(value = "editblog.htm")
	public ModelAndView toEditBlogPage(Blog blog) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		try {
			blog = service.getBlog(blog);
			if(blog == null || blog.getBlogId() == null){
				mav.addObject("msg", "没有找到该博客！");
				mav.addObject("success", "info");
			}else{
				mav.addObject("blog",blog);
			}
		} catch (ServiceException se) {
			se.printStackTrace();
			mav.addObject("msg", se.getMessage());
			mav.addObject("success", "error");
		}
		mav.setViewName("blog/editBlog");
		return mav;
	}
	
	@RequestMapping(value = "blogdetail.htm")
	public ModelAndView toBlogDetailPage(Blog blog) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		try {
			blog = service.getBlog(blog);
			if(blog == null || blog.getBlogId() == null){
				mav.addObject("msg", "没有找到该博客！");
				mav.addObject("success", "info");
			}else{
				mav.addObject("blog",blog);
			}
		} catch (ServiceException se) {
			se.printStackTrace();
			mav.addObject("msg", se.getMessage());
			mav.addObject("success", "error");
		}
		mav.setViewName("blog/blogDetail");
		return mav;
	}

	// Action
	@RequestMapping(value = "addblog.htm")
	public ModelAndView addBlog(Blog blog, BindingResult enumsErrors, RedirectAttributes modelMap) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		try {
			if (service.addBlog(blog)) {
				modelMap.addFlashAttribute("success", "success");
				modelMap.addFlashAttribute("msg", "添加成功！");
				mav.setViewName("redirect:/blog/index.htm");
			} else {
				mav.addObject("msg", "添加失败！");
				mav.addObject("blog", blog);
				mav.addObject("success", "error");
				mav.setViewName("blog/addBlog");
			}
		} catch (ServiceException se) {
			se.printStackTrace();
			logger.error(se.getMessage());
			mav.addObject("msg", se.getMessage());
			mav.addObject("blog", blog);
			mav.addObject("title", PAGE_TITLE);
			mav.addObject("success", "error");
			mav.setViewName("blog/addBlog");
		}
		return mav;
	}
	
	@RequestMapping(value = "saveblog.htm")
	public ModelAndView saveBlog(Blog blog, BindingResult enumsErrors, RedirectAttributes modelMap) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", PAGE_TITLE);
		try {
			if (service.updateBlog(blog)) {
				modelMap.addFlashAttribute("success", "success");
				modelMap.addFlashAttribute("msg", "保存成功！");
				mav.setViewName("redirect:/blog/index.htm");
			} else {
				mav.addObject("msg", "保存失败！");
				mav.addObject("blog", blog);
				mav.addObject("success", "error");
				mav.setViewName("blog/editBlog");
			}
		} catch (ServiceException se) {
			se.printStackTrace();
			logger.error(se.getMessage());
			mav.addObject("msg", se.getMessage());
			mav.addObject("blog", blog);
			mav.addObject("title", PAGE_TITLE);
			mav.addObject("success", "error");
			mav.setViewName("blog/editBlog");
		}
		return mav;
	}
	
	@RequestMapping(value = "deleteblog.htm")
	public ModelAndView deleteBlog(Blog blog, RedirectAttributes modelMap) {
		ModelAndView mav = new ModelAndView();
		try {
			service.deleteBlog(blog);
			modelMap.addFlashAttribute("success", "success");
			modelMap.addFlashAttribute("msg", "删除成功！");
		} catch (ServiceException e) {
			modelMap.addFlashAttribute("success", "error");
			modelMap.addFlashAttribute("msg", e.getMessage());
		}
		mav.setViewName("redirect:/blog/index.htm");
		return mav;
	}
}
