package org.solt.wealth.model;

import java.io.Serializable;
import java.sql.Date;

import org.solt.wealth.model.common.CommonEntity;

public class Journal extends CommonEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1075279683859893320L;

	private Long journalId;
	private String topCategory;
	private String mainCategory;
	private String subCategory;
	private Date journalDate;
	private Double quantity;
	private Double unitPrice;
	private Double discountPrice;
	private String uom;
	private String address;
	private String specification;
	private String description;
	private String item;
	private Integer accBookId;
	private Double amount;
	private String brand;
	private String barcode;

	public Long getJournalId() {
		return journalId;
	}

	public void setJournalId(Long journalId) {
		this.journalId = journalId;
	}

	public String getMainCategory() {
		return mainCategory;
	}

	public void setMainCategory(String mainCategory) {
		this.mainCategory = mainCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public Date getJournalDate() {
		return journalDate;
	}

	public void setJournalDate(Date journalDate) {
		this.journalDate = journalDate;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAccBookId() {
		return accBookId;
	}

	public void setAccBookId(Integer accBookId) {
		this.accBookId = accBookId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getTopCategory() {
		return topCategory;
	}

	public void setTopCategory(String topCategory) {
		this.topCategory = topCategory;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (journalId ^ (journalId >>> 32));
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
		Journal other = (Journal) obj;
		if (journalId != other.journalId)
			return false;
		return true;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

}
