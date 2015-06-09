package org.solt.wealth.model;

import org.solt.wealth.model.common.CommonEntity;

/**
 * Blog Model Class
 * @author salt
 *
 */
public class Blog extends CommonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1020624788937690910L;
	private Long blogId;
	private String title;
	private String content;

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
