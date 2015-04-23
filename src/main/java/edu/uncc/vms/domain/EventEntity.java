package edu.uncc.vms.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class EventEntity {

	private int eventId;
	private int userId;
	@NotEmpty
	private String eventName;
	@NotEmpty
	private String eventDescription;
	@NotEmpty
	private String eventDate;
	@NotEmpty
	private String city;
	@NotEmpty
	private String state;
	private String createdDate;

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

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "EventEntity [eventId=" + eventId + ", userId=" + userId
				+ ", eventName=" + eventName + ", eventDescription="
				+ eventDescription + ", eventDate=" + eventDate + ", city="
				+ city + ", state=" + state + ", createdDate=" + createdDate
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EventEntity) {
			EventEntity event = (EventEntity) obj;
			if (event.getEventId() == this.getEventId())
				return true;
		}
		return false;
	}
}
