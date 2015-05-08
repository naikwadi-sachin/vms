package edu.uncc.vms.web.form;

public class CommentForm {

	private int commentId;
	private int eventId;
	private int userId;
	private String firstName;
	private String lastName;
	private String comment;
	private String commentDate;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "CommentEntity [commentId=" + commentId + ", eventId=" + eventId
				+ ", userId=" + userId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", comment=" + comment
				+ ", commentDate=" + commentDate + "]";
	}


	
}
