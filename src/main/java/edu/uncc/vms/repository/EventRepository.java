package edu.uncc.vms.repository;

import java.util.List;

import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;

public interface EventRepository {

	public EVENT_STATUS_CODE addPost(EventEntity event);
	public EVENT_STATUS_CODE deletePost(EventEntity event);
	public EventEntity getPost(int eventId);
	public List<EventEntity> getPosts(EventEntity event);
	public EVENT_STATUS_CODE volunteer(EventEntity event);
	public List<EventEntity> getUserPosts(EventEntity event);
	public EVENT_STATUS_CODE editPost(EventEntity event);
}
