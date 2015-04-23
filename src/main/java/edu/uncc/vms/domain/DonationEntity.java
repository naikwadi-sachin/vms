package edu.uncc.vms.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DonationEntity {

	private int donationId;
	private int eventId;
	private int userId;
	@NotEmpty
	private String amount;
	@NotEmpty
	private String cardHolderName;
	@NotEmpty
	private String billingAddress;
	@NotEmpty
	private String cardType;
	@NotEmpty
	private String cardNumber;
	@NotEmpty
	private String expiryMonth;
	@NotEmpty
	private String expiryYear;
	@NotNull
	private Integer securityCode;
	private String donationDate;
	public int getDonationId() {
		return donationId;
	}
	public void setDonationId(int donationId) {
		this.donationId = donationId;
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
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
	public Integer getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(Integer securityCode) {
		this.securityCode = securityCode;
	}
	public String getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(String donationDate) {
		this.donationDate = donationDate;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "DonationEntity [donationId=" + donationId + ", eventId="
				+ eventId + ", userId=" + userId + ", amount=" + amount
				+ ", cardHolderName=" + cardHolderName + ", billingAddress="
				+ billingAddress + ", cardType=" + cardType + ", cardNumber="
				+ cardNumber + ", expiryMonth=" + expiryMonth + ", expiryYear="
				+ expiryYear + ", securityCode=" + securityCode
				+ ", donationDate=" + donationDate + "]";
	}
	
}
