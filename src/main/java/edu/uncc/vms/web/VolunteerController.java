package edu.uncc.vms.web;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.uncc.vms.domain.EVENT_STATUS_CODE;
import edu.uncc.vms.domain.EventEntity;
import edu.uncc.vms.domain.UserEntity;
import edu.uncc.vms.service.facade.VMSFacadeService;
import edu.uncc.vms.web.form.ControllerCodes;

@Controller
public class VolunteerController {

	@Autowired
	@Qualifier("vmsFacadeService")
	private VMSFacadeService facade;

	@RequestMapping(value = "/join/{eventId}", method = { RequestMethod.GET })
	public String joinEvent(@PathVariable("eventId") int eventId,
			HttpSession session, Model model) {
		System.out.println("joinEvent eventId=" + eventId);
		String status = "0";
		if (session.getAttribute("user") != null) {
			status = "1";
			UserEntity user = (UserEntity) session.getAttribute("user");
			EventEntity event = new EventEntity();
			event.setEventId(eventId);
			event.setUserId(user.getUserId());

			EVENT_STATUS_CODE volunteerStatus = facade.volunteer(event);
			if (volunteerStatus == EVENT_STATUS_CODE.EVENT_VOLUNTEER_DUPLICATE_ERROR) {
				status = ControllerCodes.eventJoinDuplicate;
			} else if (volunteerStatus == EVENT_STATUS_CODE.EVENT_VOLUNTEER_ERROR) {
				status = ControllerCodes.eventJoinError;
			} else if (volunteerStatus == EVENT_STATUS_CODE.EVENT_VOLUNTEER_SUCCESS) {
				status = ControllerCodes.eventJoinSuccess;
			}

			System.out.println("volunteerStatus=" + volunteerStatus);
			session.setAttribute("eventId", eventId);
		}
		return "forward:/showPosts?status=" + status;
	}

	@RequestMapping(value = "/showMyEvents", method = RequestMethod.GET)
	public String showMyEvents(Locale locale, HttpSession session, Model model) {

		if (session.getAttribute("user") != null) {

			UserEntity user = (UserEntity) session.getAttribute("user");
			EventEntity event = new EventEntity();
			event.setUserId(user.getUserId());
			List<EventEntity> events = facade.getUserPosts(event);
			System.out.println("showMyEvents events.size()=" + events.size());
			model.addAttribute("events", events);
			return "myEvents";
		}
		return "login";
	}
}
