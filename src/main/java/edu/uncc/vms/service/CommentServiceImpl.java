package edu.uncc.vms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;
import edu.uncc.vms.repository.CommentRepository;

@Service("commentService")
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public COMMENT_STATUS_CODE insertComment(CommentEntity comment) {
		return commentRepository.insertComment(comment);
	}

	@Override
	public COMMENT_STATUS_CODE deleteComment(CommentEntity comment) {
		return commentRepository.deleteComment(comment);
	}

	@Override
	public CommentEntity getComment(CommentEntity comment) {
		return commentRepository.getComment(comment);
	}

	@Override
	public List<CommentEntity> getAllComments(CommentEntity comment) {
		return commentRepository.getAllComments(comment);
	}

}
