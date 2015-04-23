package edu.uncc.vms.web;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
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
import edu.uncc.vms.web.form.ControllerCodes;

@Controller
public class EventController {

	@Autowired
	@Qualifier("vmsFacadeService")
	private VMSFacadeService facade;

	@Autowired
	private MessageSource messages;

	@RequestMapping(value = "/createPost", method = RequestMethod.GET)
	public ModelAndView showPostView() {
		return new ModelAndView("createPost", "event", new EventEntity());
	}

	@RequestMapping(value = "/createPost", method = RequestMethod.POST)
	public String post(@Valid @ModelAttribute("event") EventEntity event,
			BindingResult result, HttpSession session, Locale locale,
			Model model) {
		System.out.println("posting event " + event.toString());
		if (result.hasErrors()) {
			model.addAttribute("event", event);
			return "createPost";
		}

		if (session.getAttribute("user") != null) {
			UserEntity user = (UserEntity) session.getAttribute("user");
			event.setUserId(user.getUserId());
		}
		else
		{
			ObjectError error = new ObjectError("unauthorizedPostUser",
					messages.getMessage("event.post.unauthorized",
							new Object[] { event.getEventName() }, locale));
			result.addError(error);
			model.addAttribute("event", event);
			return "createPost";
		}
		EVENT_STATUS_CODE status = facade.addPost(event);
		System.out.println("eventCreationStatus = " + status);

		if (status.equals(EVENT_STATUS_CODE.EVENT_POST_SUCCESS)) {
			model.addAttribute("successMessage", messages.getMessage(
					"event.post.success",
					new Object[] { event.getEventName() }, locale));
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

		model.addAttribute("event", event);
		return "createPost";
	}

	@RequestMapping(value = "/showPosts", method = RequestMethod.GET)
	public String showEvents(Locale locale, HttpServletRequest request,
			HttpSession session, Model model) {
		String status = request.getParameter("status");
		int eventId = 0;
		if (session.getAttribute("eventId") != null) {
			eventId = (Integer) session.getAttribute("eventId");
			session.removeAttribute("eventId");
		}
		System.out.println("showEvents eventId=" + eventId);
		System.out.println("status=" + status);
		if (ControllerCodes.eventJoinError.equals(status))
			model.addAttribute("joinError",
					messages.getMessage("event.join.error", null, locale));
		else if (ControllerCodes.eventJoinDuplicate.equals(status))
			model.addAttribute("joinError",
					messages.getMessage("event.join.duplicate", null, locale));
		else if (ControllerCodes.eventJoinSuccess.equals(status))
			model.addAttribute("joinSuccess", messages.getMessage(
					"event.join.success", new Object[] { eventId }, locale));

		else if (ControllerCodes.eventDeleteSuccess.equals(status))
			model.addAttribute("deleteSuccess", messages.getMessage(
					"event.delete.success", new Object[] { eventId }, locale));
		else if (ControllerCodes.eventDeleteError.equals(status))
			model.addAttribute("deleteError", messages.getMessage(
					"event.delete.error", new Object[] { eventId }, locale));

		else if (ControllerCodes.logoutSuccess.equals(status))
			model.addAttribute("logoutSuccess",
					messages.getMessage("user.logout.success", null, locale));
		
		else if (ControllerCodes.donationSuccess.equals(status))
			model.addAttribute("donationSuccess",
					messages.getMessage("user.donation.sucess", null, locale));
		else if (ControllerCodes.donationError.equals(status))
			model.addAttribute("donationError",
					messages.getMessage("user.donation.error", null, locale));

		List<EventEntity> events = facade.getPosts(null);
		System.out.println("showEvents events.size()=" + events.size());
		model.addAttribute("events", events);
		model.addAttribute("event", new EventEntity());

		return "showPosts";
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

		System.out.println("event search parameters=" + event.toString());
		List<EventEntity> events = facade.getPosts(event);
		System.out.println("showEvents events.size()=" + events.size());
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
		System.out.println("in deleteEvent eventId=" + eventId);

		EventEntity event = new EventEntity();
		event.setEventId(eventId);
		EVENT_STATUS_CODE eventDeleteResult = facade.deletePost(event);
		String status = "";
		if (eventDeleteResult == EVENT_STATUS_CODE.EVENT_DELETE_SUCCESS)
			status = ControllerCodes.eventDeleteSuccess;
		else if (eventDeleteResult == EVENT_STATUS_CODE.EVENT_DELETE_ERROR)
			status = ControllerCodes.eventDeleteError;
		return "forward:/showPosts?status=" + status;
	}
	
	@RequestMapping(value="/editEvent/{eventId}", method = RequestMethod.GET)
	public String editEvent(@PathVariable("eventId") int eventId, Model model)
	{
		System.out.println("in editEvent eventId=" + eventId);
		EventEntity event = facade.getPost(eventId);
		model.addAttribute(event);
		return "createPost";
	}
}
