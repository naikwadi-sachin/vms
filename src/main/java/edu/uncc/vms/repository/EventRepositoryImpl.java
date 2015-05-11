package edu.uncc.vms.repository;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.helper.EventEntityMapper;
import edu.uncc.vms.util.Utility;

@Repository("eventRepository")
public class EventRepositoryImpl implements EventRepository {

	private static final Logger logger = Logger.getLogger(EventRepositoryImpl.class);
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private Utility utility;

	// private int testUserId = 0;

	@Override
	public EVENT_STATUS_CODE addPost(EventEntity event) {
		logger.debug("addPost " + event.toString());
		String sql = "insert into vms_event(event_name,event_description,event_date,state,city,user_id) values(?,?,?,?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, event.getEventName(),
				event.getEventDescription(), event.getEventDate(),
				event.getState(), event.getCity(), event.getUserId());
		logger.debug("addPost count=" + count);
		if (count > 0)
			return EVENT_STATUS_CODE.EVENT_POST_SUCCESS;
		else
			return EVENT_STATUS_CODE.EVENT_POST_ERROR;
	}

	@Override
	public EVENT_STATUS_CODE deletePost(EventEntity event) {
		String sql = "update vms_event set status = 'n' where event_id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, event.getEventId());
		logger.debug("deletePost count=" + count);
		
		if (count > 0) {
			try {
				String sqlNew = "update vms_comments set status = 'n' where event_id=?";
				int countNew = jdbcTemplate.update(sqlNew, event.getEventId());
				logger.debug("deletePost deletecomments countNew="
						+ countNew);
			} catch (Exception e) {
				logger.error("exception in deletePost " + e.getMessage());
			}
			return EVENT_STATUS_CODE.EVENT_DELETE_SUCCESS;

		}

		else
			return EVENT_STATUS_CODE.EVENT_DELETE_ERROR;
	}

	@Override
	public EventEntity getPost(int eventId) {
		try
		{
		String sql = "select event_id,user_id,event_name,event_description,city,state,event_date,created_date from vms_event where event_id = ? limit 1";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// int count = jdbcTemplate.execute(sql, eventId);
		EventEntity event = jdbcTemplate.queryForObject(sql,
				new Object[] { eventId },
				new BeanPropertyRowMapper<EventEntity>(EventEntity.class));

		logger.debug("getPost event=" + event);
		
		return event;
		}
		catch(DataAccessException dae)
		{
			dae.printStackTrace();
			return null;
		}
	}

	@Override
	public List<EventEntity> getPosts(EventEntity event) {

		String sql = " select event_id,user_id,event_name,event_description,event_date,state,city,created_date from vms_event where status='y' ";
		String where = "";
		String orderBy = " order by event_date desc";
		if (event != null) {

			if (!utility.isEmpty(event.getEventName())) {
				where = " and event_name like '%" + event.getEventName() + "%'";
			}
			if (!utility.isEmpty(event.getEventDate())) {
				where = where + " and event_date = '" + event.getEventDate()
						+ "'";
			}
			if (!utility.isEmpty(event.getState())) {
				where = where + " and state like '%" + event.getState() + "%'";
			}
			if (!utility.isEmpty(event.getCity())) {
				where = where + " and city like '%" + event.getCity() + "%'";
			}
		}

		sql = sql + where + orderBy;

		logger.debug("sql:" + sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<EventEntity> events = jdbcTemplate.query(sql,
				new EventEntityMapper());
		return events;
	}

	@Override
	public List<EventEntity> getUserPosts(EventEntity event) {

		String sql = " SELECT u.event_id,u.user_id,event_name,event_description,event_date,state,city,join_date as created_date ";
		String form = " FROM vms.vms_user_events u, vms_event e ";
		String where = " where u.event_id = e.event_id and u.status = 'y' and u.user_id= '"
				+ event.getUserId() + "'";
		String orderBy = " order by e.event_date desc";

		sql = sql + form + where + orderBy;

		logger.debug("getUserPosts sql:" + sql);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<EventEntity> events = jdbcTemplate.query(sql,
				new EventEntityMapper());
		return events;
	}

	@Override
	public EVENT_STATUS_CODE volunteer(EventEntity event) {
		logger.debug("volunteer = " + event.toString());
		String sql = "insert into vms_user_events(user_id,event_id) values(?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = 0;
		try {
			count = jdbcTemplate.update(sql, event.getUserId(),
					event.getEventId());
		} catch (DataAccessException e) {
			return EVENT_STATUS_CODE.EVENT_VOLUNTEER_DUPLICATE_ERROR;
		}
		logger.debug("volunteer count=" + count);
		if (count > 0)
			return EVENT_STATUS_CODE.EVENT_VOLUNTEER_SUCCESS;
		else
			return EVENT_STATUS_CODE.EVENT_VOLUNTEER_ERROR;

	}

	@Override
	public EVENT_STATUS_CODE editPost(EventEntity event) {

		logger.debug("editPost " + event.toString());
		String sql = "update vms_event "
				+ "set event_name = ?,event_description = ?,event_date = ?,state =?,city=?,user_id=? "
				+ "where event_id=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, event.getEventName(),
				event.getEventDescription(), event.getEventDate(),
				event.getState(), event.getCity(), event.getUserId(), event.getEventId());
		logger.debug("editPost count=" + count);
		
		if (count > 0)
			return EVENT_STATUS_CODE.EVENT_EDIT_SUCCESS;
		else
			return EVENT_STATUS_CODE.EVENT_EDIT_ERROR;
	}

}
