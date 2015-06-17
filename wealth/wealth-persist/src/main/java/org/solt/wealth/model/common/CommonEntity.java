package org.solt.wealth.model.common;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

/**
 * 抽象通用实体类
 * @author yasin
 */
public abstract class CommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8022254739380873216L;
	private Timestamp creationDate;
	private Long createdBy;
	private Timestamp lastUpdatedDate;
	private Long lastUpdatedBy;

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Map<String, Object> toMap() {
		return null;
	}

}
