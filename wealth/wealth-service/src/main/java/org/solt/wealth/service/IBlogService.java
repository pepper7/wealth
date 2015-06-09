package org.solt.wealth.service;

import java.util.List;

import org.solt.wealth.model.Blog;

public interface IBlogService {

	public Blog getBlog(Blog blog) throws ServiceException;

	public List<Blog> findBlogs(Blog blog) throws ServiceException;

	public boolean addBlog(Blog blog) throws ServiceException;

	public boolean updateBlog(Blog blog) throws ServiceException;

	public boolean deleteBlog(Blog blog) throws ServiceException;

}
