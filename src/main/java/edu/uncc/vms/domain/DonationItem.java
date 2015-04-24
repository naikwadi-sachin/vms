package edu.uncc.vms.domain;

public class DonationItem {

	private String eventName;
	private String userName;
	private String email;
	private String donationCategory;
	private String donation;
	private String donationDate;
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "DonationItem [eventName=" + eventName + ", userName="
				+ userName + ", email=" + email + ", donationCategory="
				+ donationCategory + ", donation=" + donation
				+ ", donationDate=" + donationDate + "]";
	}
	
	
}
