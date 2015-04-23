package edu.uncc.vms.domain.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.uncc.vms.domain.EventEntity;

public class EventEntityMapper implements RowMapper<EventEntity>{

	@Override
	public EventEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventEntity event = new EventEntity();
		event.setEventId(rs.getInt("event_id"));
		event.setEventName(rs.getString("event_name"));
		event.setEventDescription(rs.getString("event_description"));
		event.setEventDate(rs.getString("event_date"));
		event.setUserId(rs.getInt("user_id"));
		event.setState(rs.getString("state"));
		event.setCity(rs.getString("city"));
		event.setCreatedDate(rs.getString("created_date"));
		return event;
	}

}
