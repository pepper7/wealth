package org.solt.wealth.model.common;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Enums extends CommonEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1489618730247110166L;

	@NotEmpty(message = "{category.enumId.empty.error}")
	@Length(max = 20, message = "{category.enumId.length.error}")
	private String enumId;
	@NotEmpty(message = "{category.enumType.empty.error}")
	@Length(max = 20, message = "{category.enumType.length.error}")
	private String enumType;
	@NotEmpty(message = "{category.enumName.empty.error}")
	@Length(max = 50, message = "{category.enumName.length.error}")
	private String enumName;
	@Length(max = 200, message = "{category.description.length.error}")
	private String description;
	@Length(max = 20, message = "{category.parentEnumId.length.error}")
	private String parentEnumId;

	public String getEnumId() {
		return enumId;
	}

	public void setEnumId(String enumId) {
		this.enumId = enumId;
	}

	public String getEnumType() {
		return enumType;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentEnumId() {
		return parentEnumId;
	}

	public void setParentEnumId(String parentEnumId) {
		this.parentEnumId = parentEnumId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enumId == null) ? 0 : enumId.hashCode());
		result = prime * result + ((enumType == null) ? 0 : enumType.hashCode());
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
		Enums other = (Enums) obj;
		if (enumId == null) {
			if (other.enumId != null)
				return false;
		} else if (!enumId.equals(other.enumId))
			return false;
		if (enumType == null) {
			if (other.enumType != null)
				return false;
		} else if (!enumType.equals(other.enumType))
			return false;
		return true;
	}

}
