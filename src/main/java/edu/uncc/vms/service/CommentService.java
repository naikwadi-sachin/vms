package edu.uncc.vms.service;

import java.util.List;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;

public interface CommentService {

	public COMMENT_STATUS_CODE insertComment(CommentEntity comment);
	public COMMENT_STATUS_CODE deleteComment(CommentEntity comment);
	public CommentEntity getComment(CommentEntity comment);
	public List<CommentEntity> getAllComments(CommentEntity comment);
}
