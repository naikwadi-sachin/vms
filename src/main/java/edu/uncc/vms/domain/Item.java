package edu.uncc.vms.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class Item {

	private int itemId;
	@NotEmpty
	private String itemCategory;
	@NotEmpty
	private String itemDescription;
	private int eventId;
	private int userId;
	private String itemDate;
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getItemDate() {
		return itemDate;
	}
	public void setItemDate(String itemDate) {
		this.itemDate = itemDate;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemCategory=" + getItemCategory()
				+ ", itemDescription=" + itemDescription + ", eventId="
				+ eventId + ", userId=" + userId + ", itemDate=" + itemDate
				+ "]";
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	
}
