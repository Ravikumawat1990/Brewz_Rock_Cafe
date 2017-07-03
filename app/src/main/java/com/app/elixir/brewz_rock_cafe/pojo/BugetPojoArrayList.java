package com.app.elixir.brewz_rock_cafe.pojo;

public class BugetPojoArrayList {

	String allocated;
	String spent;
	String note;
	String productName;
	String isDeleted;
	String isSyns;

	public String getIsSyns() {
		return isSyns;
	}

	public void setIsSyns(String isSyns) {
		this.isSyns = isSyns;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	int budgetId;
	int userId;
	int weddingId;
	int offlineVendorID;

	public String getProductName() {
		return productName;
	}

	public int getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(int budgetId) {
		this.budgetId = budgetId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getWeddingId() {
		return weddingId;
	}

	public void setWeddingId(int weddingId) {
		this.weddingId = weddingId;
	}

	public int getOfflineVendorID() {
		return offlineVendorID;
	}

	public void setOfflineVendorID(int offlineVendorID) {
		this.offlineVendorID = offlineVendorID;
	}

	public String getAllocated() {
		return allocated;
	}

	public void setAllocated(String allocated) {
		this.allocated = allocated;
	}

	public String getSpent() {
		return spent;
	}

	public void setSpent(String spent) {
		this.spent = spent;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
