package edu.uncc.vms.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.service.facade.VMSFacadeService;
import edu.uncc.vms.util.Utility;
import edu.uncc.vms.web.form.ControllerCodes;
import edu.uncc.vms.web.form.EventForm;
import edu.uncc.vms.web.form.LoginForm;

@Controller
public class EventController {

	private static final Logger logger = Logger
			.getLogger(EventController.class);

	@Autowired
	@Qualifier("vmsFacadeService")
	private VMSFacadeService facade;

	@Autowired
	private MessageSource messages;

	@Autowired
	private Utility utility;

	@RequestMapping(value = "/createPost", method = RequestMethod.GET)
	public ModelAndView showPostView(HttpSession session) {
		if (session.getAttribute("user") == null) {
			return new ModelAndView("login", "login", new LoginForm());
		}
		return new ModelAndView("createPost", "event", new EventForm());
	}

	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public String post(@Valid @ModelAttribute("event") EventForm event,
			BindingResult result, HttpServletRequest request,
			HttpSession session, Locale locale, Model model) {
		logger.debug("posting event " + event.toString());
		logger.debug("event mode" + request.getParameter("editEvent"));
		UserEntity user;

		try {
			int status = utility.checkDate(event.getEventDate());
			// logger.debug("date check status="+status);
			if (status < 1) {
				ObjectError error = new ObjectError("InvalidDate",
						messages.getMessage("Future.event.eventDate",
								new Object[] { event.getEventName() }, locale));
				result.addError(error);
			}
		} catch (ParseException pe) {
			ObjectError error = new ObjectError("InvalidDate",
					messages.getMessage("Format.event.eventDate",
							new Object[] { event.getEventName() }, locale));
			result.addError(error);
		}

		if (result.hasErrors()) {
			model.addAttribute("event", event);
			return "createPost";
		}

		if (session.getAttribute("user") != null) {
			user = (UserEntity) session.getAttribute("user");
			event.setUserId(user.getUserId());
		} else {
			ObjectError error = new ObjectError("unauthorizedPostUser",
					messages.getMessage("event.post.unauthorized",
							new Object[] { event.getEventName() }, locale));
			result.addError(error);
			model.addAttribute("event", event);
			return "createPost";
		}

		EventEntity sevent = new EventEntity();
		sevent.setCity(event.getCity());
		sevent.setCreatedDate(event.getCreatedDate());
		sevent.setEventDate(event.getEventDate());
		sevent.setEventDescription(event.getEventDescription());
		sevent.setEventId(event.getEventId());
		sevent.setEventName(event.getEventName());
		sevent.setState(event.getState());
		sevent.setUserId(event.getUserId());

		if (request.getParameter("editEvent") != null) {
			EVENT_STATUS_CODE status = facade.editPost(sevent);
			logger.debug("eventUpdationStatus = " + status);
			if (status.equals(EVENT_STATUS_CODE.EVENT_EDIT_SUCCESS)) {
				model.addAttribute("successMessage", messages.getMessage(
						"event.edit.success",
						new Object[] { event.getEventName() }, locale));
				return "success";
			} else if (status.equals(EVENT_STATUS_CODE.EVENT_EDIT_ERROR)) {
				ObjectError error = new ObjectError("duplicateEvent",
						messages.getMessage("event.edit.error",
								new Object[] { event.getEventName() }, locale));
				result.addError(error);
			}
		} else {
			EVENT_STATUS_CODE status = facade.addPost(sevent);
			logger.debug("eventCreationStatus = " + status);

			if (status.equals(EVENT_STATUS_CODE.EVENT_POST_SUCCESS)) {
				model.addAttribute("successMessage", messages.getMessage(
						"event.post.success",
						new Object[] { event.getEventName() }, locale));

				try {
					facade.sendEmail(user.getEmail(),
							"Event " + event.getEventName() + " is posted!",
							"Thank you for posting event in VMS");
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "success";
			} else if (status.equals(EVENT_STATUS_CODE.EVENT_POST_ERROR)) {
				ObjectError error = new ObjectError("duplicateEvent",
						messages.getMessage("event.post.error",
								new Object[] { event.getEventName() }, locale));
				result.addError(error);
			} else {

				ObjectError error = new ObjectError("eventPostingError",
						messages.getMessage("event.post.error",
								new Object[] { event.getEventName() }, locale));
				result.addError(error);
			}
		}
		model.addAttribute("event", event);
		return "createPost";
	}

	@RequestMapping(value = "/showPosts", method = RequestMethod.GET)
	public String showEvents(Locale locale, HttpServletRequest request,
			HttpSession session, Model model) {
		String status = request.getParameter("status");
		int eventId = 0;
		EventEntity event = null;
		try {
			if (request.getAttribute("eventId") != null) {
				eventId = Integer
						.parseInt("" + request.getAttribute("eventId"));
				event = facade.getPost(eventId);
			}
			logger.debug("showEvents eventId="
					+ request.getAttribute("eventId"));
			if (session.getAttribute("eventId") != null) {
				eventId = (Integer) session.getAttribute("eventId");
				session.removeAttribute("eventId");
			}
			logger.debug("status=" + status);
			if (ControllerCodes.eventJoinError.equals(status))
				model.addAttribute("joinError",
						messages.getMessage("event.join.error", null, locale));
			else if (ControllerCodes.eventJoinDuplicate.equals(status))
				model.addAttribute("joinError", messages.getMessage(
						"event.join.duplicate",
						new Object[] { event.getEventName() }, locale));
			else if (ControllerCodes.eventJoinSuccess.equals(status))
				model.addAttribute("joinSuccess", messages.getMessage(
						"event.join.success",
						new Object[] { event.getEventName() }, locale));

			else if (ControllerCodes.eventDeleteSuccess.equals(status))
				model.addAttribute("deleteSuccess", messages.getMessage(
						"event.delete.success",
						new Object[] { event.getEventName() }, locale));
			else if (ControllerCodes.eventDeleteError.equals(status))
				model.addAttribute("deleteError", messages.getMessage(
						"event.delete.error",
						new Object[] { event.getEventName() }, locale));

			else if (ControllerCodes.logoutSuccess.equals(status))
				model.addAttribute("logoutSuccess", messages.getMessage(
						"user.logout.success", null, locale));

			else if (ControllerCodes.donationSuccess.equals(status))
				model.addAttribute("donationSuccess", messages.getMessage(
						"user.donation.sucess", null, locale));
			else if (ControllerCodes.donationError.equals(status))
				model.addAttribute("donationError", messages.getMessage(
						"user.donation.error", null, locale));

			List<EventEntity> events = facade.getPosts(null);
			logger.debug("showEvents events.size()=" + events.size());
			model.addAttribute("events", events);
			model.addAttribute("event", new EventEntity());

			return "showPosts";
		} catch (CannotGetJdbcConnectionException ce) {

			model.addAttribute("dbError",
					messages.getMessage("db.error", null, locale));
			model.addAttribute("login", new LoginForm());
			return "login";
		}
	}

	@RequestMapping(value = "/showPosts", method = RequestMethod.POST)
	public String searchEvents(@ModelAttribute("event") EventEntity event,
			HttpServletRequest request, Locale locale, Model model) {
		String status = request.getParameter("status");
		if (ControllerCodes.loginSuccess.equals(status))
			model.addAttribute("loginSuccess",
					messages.getMessage("user.login.success", null, locale));

		else if (ControllerCodes.donationSuccess.equals(status))
			model.addAttribute("donationSuccess",
					messages.getMessage("user.donation.sucess", null, locale));
		else if (ControllerCodes.donationError.equals(status))
			model.addAttribute("donationError",
					messages.getMessage("user.donation.error", null, locale));

		logger.debug("event search parameters=" + event.toString());
		List<EventEntity> events = facade.getPosts(event);
		logger.debug("showEvents events.size()=" + events.size());
		if (events.size() == 0) {
			model.addAttribute("searchResult",
					messages.getMessage("event.search.result", null, locale));
		}
		model.addAttribute("events", events);
		model.addAttribute("event", event);
		return "showPosts";
	}

	@RequestMapping(value = "/deleteEvent/{eventId}", method = RequestMethod.GET)
	public String deleteEvent(@PathVariable("eventId") int eventId, Model model) {
		logger.debug("in deleteEvent eventId=" + eventId);
		EventEntity event = new EventEntity();
		event.setEventId(eventId);
		EVENT_STATUS_CODE eventDeleteResult = facade.deletePost(event);
		String status = "";
		logger.debug("deleteEvent eventDeleteResult=" + eventDeleteResult);
		if (eventDeleteResult == EVENT_STATUS_CODE.EVENT_DELETE_SUCCESS)
			status = ControllerCodes.eventDeleteSuccess;
		else if (eventDeleteResult == EVENT_STATUS_CODE.EVENT_DELETE_ERROR)
			status = ControllerCodes.eventDeleteError;
		model.addAttribute("eventId", eventId);
		return "forward:/showPosts?status=" + status;
	}

	@RequestMapping(value = "/editEvent/{eventId}", method = RequestMethod.GET)
	public String editEvent(@PathVariable("eventId") int eventId, Model model) {
		logger.debug("in editEvent eventId=" + eventId);
		EventEntity event = facade.getPost(eventId);
		model.addAttribute("event", event);
		model.addAttribute("status", "1");
		return "createPost";
	}
}
