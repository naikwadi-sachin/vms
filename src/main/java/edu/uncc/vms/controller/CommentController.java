package edu.uncc.vms.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.uncc.vms.domain.COMMENT_STATUS_CODE;
import edu.uncc.vms.domain.CommentEntity;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.service.facade.VMSFacadeService;

@Controller
@SessionAttributes("user")
public class CommentController {

	private static final Logger logger = Logger.getLogger(CommentController.class);
	
	@Autowired
	@Qualifier("vmsFacadeService")
	private VMSFacadeService facade;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/comments/{eventId}", method = { RequestMethod.GET })
	public String showComments(@PathVariable("eventId") int eventId,
			HttpServletRequest request, Locale locale, Model model) {
		// List<EventEntity> events = eventService.getPosts(event);
//		logger.debug("showEvents events.size()=" + events.size());
		logger.debug("showComments eventId=" + eventId);

		String status = request.getParameter("status");
		if ("-1".equals(status))
			model.addAttribute("commentError",
					messages.getMessage("comment.delete.error", null, locale));
		else if ("0".equals(status))
			model.addAttribute("commentSuccess",
					messages.getMessage("comment.delete.success", null, locale));

		CommentEntity comment = new CommentEntity();
		comment.setEventId(eventId);
		List<CommentEntity> comments = facade.getAllComments(comment);
		logger.debug("showComments comments.size()=" + comments.size());
		model.addAttribute("comments", comments);
		model.addAttribute("comment", comment);
		model.addAttribute("event", facade.getPost(eventId));
		return "showComments";
	}

	@RequestMapping(value = "/comments", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("comment") CommentEntity comment,
			HttpServletRequest request, Locale locale, Model model) {
		logger.debug("addComment comment=" + comment.toString());
		try {
			UserEntity user = (UserEntity) request.getSession(false)
					.getAttribute("user");
			comment.setUserId(user.getUserId());
		} catch (NullPointerException npe) {
		}
		COMMENT_STATUS_CODE addCommentResult = facade.insertComment(comment);
		logger.debug("addCommentResult=" + addCommentResult);
		comment.setComment("");

		if (addCommentResult == COMMENT_STATUS_CODE.COMMENT_INSERT_SUCCESS) {
			model.addAttribute("commentSuccess",
					messages.getMessage("comment.insert.success", null, locale));
		} else if (addCommentResult == COMMENT_STATUS_CODE.COMMENT_INSERT_SUCCESS) {
			model.addAttribute("commentError",
					messages.getMessage("comment.insert.error", null, locale));
		}

		model.addAttribute("comment", comment);
		List<CommentEntity> comments = facade.getAllComments(comment);
		logger.debug("addComment comments.size()=" + comments.size());
		model.addAttribute("comments", comments);

		EventEntity event = facade.getPost(comment.getEventId());
		model.addAttribute("event", event);
		return "showComments";
	}

	@RequestMapping(value = "/deleteComment/{eventId}/{commentId}", method = RequestMethod.GET)
	public String deleteComment(@PathVariable("eventId") int eventId,
			@PathVariable("commentId") int commentId, HttpSession session) {
		logger.debug("deleteComment commentId=" + commentId);
		logger.debug("deleteComment eventId=" + eventId);
		CommentEntity comment = new CommentEntity();
		comment.setCommentId(commentId);
		comment.setEventId(eventId);
		COMMENT_STATUS_CODE commentStatus = facade.deleteComment(comment);
		logger.debug("deleteComment commentStatus=" + commentStatus);
		String status = "";
		if (commentStatus == COMMENT_STATUS_CODE.COMMENT_DELETE_ERROR) {
			status = "-1";
		} else if (commentStatus == COMMENT_STATUS_CODE.COMMENT_DELETE_SUCCESS) {
			status = "0";
		}
		return "forward:/comments/" + eventId + "?status=" + status;
	}
}
