package edu.uncc.vms.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.helper.CommentEntityMapper;
import edu.uncc.vms.domain.helper.EventEntityMapper;

@Repository("commentRepository")
public class CommentRepositoryImpl implements CommentRepository {

	@Autowired
	private DataSource dataSource;
	private int testUserId = 1;

	@Override
	public COMMENT_STATUS_CODE insertComment(CommentEntity comment) {
		String sql = "insert into vms_comments(event_id,user_id,comment) values(?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, comment.getEventId(),
				comment.getUserId(), comment.getComment());
		System.out.println("insertComment count=" + count);

		if (count > 0)
			return COMMENT_STATUS_CODE.COMMENT_INSERT_SUCCESS;
		else
			return COMMENT_STATUS_CODE.COMMENT_INSERT_ERROR;

	}

	@Override
	public COMMENT_STATUS_CODE deleteComment(CommentEntity comment) {
		String sql = "update vms_comments set status = 'n' where comment_id=?";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.update(sql, comment.getCommentId());
		System.out.println("deleteComment count=" + count);

		if (count == 1)
			return COMMENT_STATUS_CODE.COMMENT_DELETE_SUCCESS;
		else
			return COMMENT_STATUS_CODE.COMMENT_DELETE_ERROR;

	}

	@Override
	public CommentEntity getComment(CommentEntity comment) {
		return null;
	}

	@Override
	public List<CommentEntity> getAllComments(CommentEntity comment) {
		List<String> list = new ArrayList<String>();
		list.add("event_name");

		// String
		// sql=" select comment_id,event_id,user_id,comment,comment_date from vms_comments where 1=1 ";
		String sql = "select comment_id,event_id,c.user_id,u.first_name,u.last_name,comment,comment_date "
				+ "from vms_comments c inner join vms_user u "
				+ "on c.user_id = u.user_id where status = 'y' ";

		String where = "";
		String orderBy = " order by comment_date desc";
		if (comment != null) {
			if (comment.getEventId() != 0) {
				where += " and event_id ='" + comment.getEventId() + "'";
			}
		}

		sql = sql + " " + where + " " + orderBy;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<CommentEntity> comments = jdbcTemplate.query(sql,
				new CommentEntityMapper());
		return comments;
	}

}
