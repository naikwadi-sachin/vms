package edu.uncc.vms.domain;

public class DonationItem {

	private String eventName;
	private String donatedBy;
	private String donationCategory;
	private String donation;
	private String donationDate;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getDonatedBy() {
		return donatedBy;
	}
	public void setDonatedBy(String donatedBy) {
		this.donatedBy = donatedBy;
	}
	public String getDonationCategory() {
		return donationCategory;
	}
	public void setDonationCategory(String donationCategory) {
		this.donationCategory = donationCategory;
	}
	public String getDonation() {
		return donation;
	}
	public void setDonation(String donation) {
		this.donation = donation;
	}
	public String getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(String donationDate) {
		this.donationDate = donationDate;
	}
	@Override
	public String toString() {
		return "DonationItem [eventName=" + eventName + ", donatedBy="
				+ donatedBy + ", donationCategory=" + donationCategory
				+ ", donation=" + donation + ", donationDate=" + donationDate
				+ "]";
	}
	
	
}
