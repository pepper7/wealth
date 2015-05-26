package org.solt.wealth.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.solt.wealth.model.common.CommonEntity;

public class UserLogin extends CommonEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3570360455606734701L;
	@NotEmpty(message = "{userLogin.userLoginId.empty.error}")
	@Length(max = 40, message = "{userLogin.userLoginId.length.error}")
	private String userLoginId;
	@NotEmpty(message = "{userLogin.password.empty.error}")
	@Length(max = 40, message = "{userLogin.password.length.error}")
	private String password;
	@Length(max = 20, message = "{userLogin.status.length.error}")
	private String status;

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userLoginId == null) ? 0 : userLoginId.hashCode());
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
		UserLogin other = (UserLogin) obj;
		if (userLoginId == null) {
			if (other.userLoginId != null)
				return false;
		} else if (!userLoginId.equals(other.userLoginId))
			return false;
		return true;
	}

}
