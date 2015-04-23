package edu.uncc.vms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.repository.EventRepository;

@Service("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Override
	public EVENT_STATUS_CODE addPost(EventEntity event) {
		return eventRepository.addPost(event);
	}

	@Override
	public EVENT_STATUS_CODE deletePost(EventEntity event) {
		return eventRepository.deletePost(event);
	}

	@Override
	public EventEntity getPost(int eventId) {
		return eventRepository.getPost(eventId);
	}

	@Override
	public List<EventEntity> getPosts(EventEntity event) {
		return eventRepository.getPosts(event);
	}

	@Override
	public EVENT_STATUS_CODE volunteer(EventEntity event) {
		return eventRepository.volunteer(event);
	}

	@Override
	public List<EventEntity> getUserPosts(EventEntity event) {
		return eventRepository.getUserPosts(event);
	}

}
