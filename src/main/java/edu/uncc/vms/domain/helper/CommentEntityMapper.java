package edu.uncc.vms.domain.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.uncc.vms.domain.CommentEntity;

public class CommentEntityMapper implements RowMapper<CommentEntity>{

	@Override
	public CommentEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		CommentEntity comment = new CommentEntity();
		comment.setCommentId(rs.getInt("comment_id"));
		comment.setEventId(rs.getInt("event_id"));
		comment.setUserId(rs.getInt("user_id"));
		comment.setFirstName(rs.getString("first_name"));
		comment.setLastName(rs.getString("last_name"));
		comment.setComment(rs.getString("comment"));
		comment.setCommentDate(rs.getString("comment_date"));
		return comment;
	}

}
