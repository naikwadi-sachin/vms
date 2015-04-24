package edu.uncc.vms.service.facade;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;
import edu.uncc.vms.domain.DONATION_STATUS_CODE;
import edu.uncc.vms.domain.DonationEntity;
import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.Item;
import edu.uncc.vms.domain.USER_STATUS_CODE;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.service.CommentService;
import edu.uncc.vms.service.DonationService;
import edu.uncc.vms.service.EventService;
import edu.uncc.vms.service.NotificationService;
import edu.uncc.vms.service.UserService;

@Service("vmsFacadeService")
public class VMSFacadeServiceImpl implements VMSFacadeService{

	@Autowired
	private UserService userService;
	@Autowired
	private EventService eventService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private DonationService donationService;
	@Autowired
	private NotificationService notificationService;
	
	@Override
	public UserEntity checkUser(UserEntity user) {
		return userService.checkUser(user);
	}

	@Override
	public USER_STATUS_CODE register(UserEntity user) {
		return userService.register(user);
	}

	@Override
	public EVENT_STATUS_CODE addPost(EventEntity event) {
		return eventService.addPost(event);
	}

	@Override
	public EVENT_STATUS_CODE deletePost(EventEntity event) {
		return eventService.deletePost(event);
	}

	@Override
	public EventEntity getPost(int eventId) {
		return eventService.getPost(eventId);
	}

	@Override
	public List<EventEntity> getPosts(EventEntity event) {
		return eventService.getPosts(event);
	}

	@Override
	public EVENT_STATUS_CODE volunteer(EventEntity event) {
		return eventService.volunteer(event);
	}

	@Override
	public List<EventEntity> getUserPosts(EventEntity event) {
		return eventService.getUserPosts(event);
	}

	@Override
	public COMMENT_STATUS_CODE insertComment(CommentEntity comment) {
		return commentService.insertComment(comment);
	}

	@Override
	public COMMENT_STATUS_CODE deleteComment(CommentEntity comment) {
		return commentService.deleteComment(comment);
	}

	@Override
	public CommentEntity getComment(CommentEntity comment) {
		return commentService.getComment(comment);
	}

	@Override
	public List<CommentEntity> getAllComments(CommentEntity comment) {
		return commentService.getAllComments(comment);
	}

	@Override
	public DONATION_STATUS_CODE insertDonation(DonationEntity donation) {
		return donationService.insertDonation(donation);
	}

	@Override
	public ArrayList<DonationEntity> getDonations(DonationEntity donation) {
		return donationService.getDonations(donation);
	}

	@Override
	public EVENT_STATUS_CODE editPost(EventEntity event) {
		return eventService.editPost(event);
	}

	@Override
	public void sendEmail(String toAddress, String subject, String message)
			throws AddressException, MessagingException {
		notificationService.sendEmail(toAddress, subject, message);
		
	}

	@Override
	public DONATION_STATUS_CODE insertItemDonation(Item item) {
		return donationService.insertItemDonation(item);
	}

}
