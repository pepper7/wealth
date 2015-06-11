package org.solt.wealth.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.solt.wealth.model.Blog;
import org.solt.wealth.persist.BlogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlogService implements IBlogService {
	private static final Logger logger = LoggerFactory.getLogger(BlogService.class);

	@Autowired
	private BlogDAO dao;

	public Blog getBlog(Blog blog) throws ServiceException {
		try {
			return dao.findByKey(blog);
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
	}

	public List<Blog> findBlogs(Blog blog) throws ServiceException {
		try {
			return dao.findList(blog);
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
	}

	public boolean addBlog(Blog blog) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.insert(blog) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean updateBlog(Blog blog) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.update(blog) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

	public boolean deleteBlog(Blog blog) throws ServiceException {
		boolean flag = false;
		try {
			if (dao.delete(blog) > 0) {
				flag = true;
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new ServiceException("数据库访问异常！");
		}
		return flag;
	}

}
