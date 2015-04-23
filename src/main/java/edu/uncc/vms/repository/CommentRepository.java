package edu.uncc.vms.repository;

import java.util.List;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;

public interface CommentRepository {

	public COMMENT_STATUS_CODE insertComment(CommentEntity comment);
	public COMMENT_STATUS_CODE deleteComment(CommentEntity comment);
	public CommentEntity getComment(CommentEntity comment);
	public List<CommentEntity> getAllComments(CommentEntity comment);
	
}
