package org.solt.wealth.persist;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.solt.wealth.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 * Blog DAO Class
 * @author salt
 */
@Repository
public class BlogDAO implements IBlogDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public Blog getBlog(Blog model) throws DataAccessException {
		return sqlSession.selectOne("org.solt.wealth.model.Blog.getBlog", model);
	}

	public List<Blog> findBlogByParam(Blog model) throws DataAccessException {
		return sqlSession.selectList("org.solt.wealth.model.Blog.findBlogByParam", model);
	}

	public long countBlogByParam(Blog model) throws DataAccessException {
		return sqlSession.selectOne("org.solt.wealth.model.Blog.countPartyByParam", model);
	}

	public int insertBlog(Blog model) throws DataAccessException {
		return sqlSession.insert("org.solt.wealth.model.Blog.insertBlog", model);
	}

	public int updateBlog(Blog model) throws DataAccessException {
		return sqlSession.update("org.solt.wealth.model.Blog.updateBlog", model);
	}

	public int deleteBlog(Blog model) throws DataAccessException {
		return sqlSession.delete("org.solt.wealth.model.Blog.deleteBlog", model);
	}

}
