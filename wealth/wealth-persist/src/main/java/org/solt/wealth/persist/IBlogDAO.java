package org.solt.wealth.persist;

import java.util.List;

import org.springframework.dao.DataAccessException;

import org.solt.wealth.model.Blog;

/**
 * Blog DAO Interface
 * @author salt
 */
public interface IBlogDAO {

	Blog getBlog(Blog model) throws DataAccessException;

	List<Blog> findBlogByParam(Blog model) throws DataAccessException;

	long countBlogByParam(Blog model) throws DataAccessException;

	int insertBlog(Blog model) throws DataAccessException;

	int updateBlog(Blog model) throws DataAccessException;

	int deleteBlog(Blog model) throws DataAccessException;
}
