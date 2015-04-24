package edu.uncc.vms.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;
import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.Item;
import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;

public interface VMSFacadeService {

	public UserEntity checkUser(UserEntity user);

	public USER_STATUS_CODE register(UserEntity user);

	public EVENT_STATUS_CODE addPost(EventEntity event);

	public EVENT_STATUS_CODE deletePost(EventEntity event);

	public EventEntity getPost(int eventId);

	public List<EventEntity> getPosts(EventEntity event);

	public EVENT_STATUS_CODE volunteer(EventEntity event);

	public List<EventEntity> getUserPosts(EventEntity event);
	
	public EVENT_STATUS_CODE editPost(EventEntity event);

	public COMMENT_STATUS_CODE insertComment(CommentEntity comment);

	public COMMENT_STATUS_CODE deleteComment(CommentEntity comment);

	public CommentEntity getComment(CommentEntity comment);

	public List<CommentEntity> getAllComments(CommentEntity comment);

	public DONATION_STATUS_CODE insertDonation(DonationEntity donation);

	public ArrayList<DonationEntity> getDonations(DonationEntity donation);
	
	public DONATION_STATUS_CODE insertItemDonation(Item item);
	
	public void sendEmail(String toAddress, String subject, String message)
			throws AddressException, MessagingException;
}
