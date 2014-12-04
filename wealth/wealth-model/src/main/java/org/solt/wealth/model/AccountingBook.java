package org.solt.wealth.model;

import java.io.Serializable;

import org.solt.wealth.model.common.CommonEntity;

public class AccountingBook extends CommonEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8137294886539879468L;

	private Integer accBookId;
	private String accBookName;
	private String description;

	public Integer getAccBookId() {
		return accBookId;
	}

	public void setAccBookId(Integer accBookId) {
		this.accBookId = accBookId;
	}

	public String getAccBookName() {
		return accBookName;
	}

	public void setAccBookName(String accBookName) {
		this.accBookName = accBookName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accBookId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountingBook other = (AccountingBook) obj;
		if (accBookId != other.accBookId)
			return false;
		return true;
	}

}
